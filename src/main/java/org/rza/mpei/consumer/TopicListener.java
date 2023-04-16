package org.rza.mpei.consumer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.rza.mpei.dto.Measurement;
import org.rza.mpei.repositories.RepositoryDto;

@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
@Service
public class TopicListener {

    @Autowired
    private RepositoryDto repository;

    @Value("${topic.name.consumer}")
    private String topicName;
  
    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, Measurement> payload) {
        log.info("Topic: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
        printMeasurment(payload.value());
   }

   public void printMeasurment(Measurement measurement){
       repository.save(measurement);
   }

}
