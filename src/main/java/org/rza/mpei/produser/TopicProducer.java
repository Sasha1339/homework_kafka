package org.rza.mpei.produser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mpei.dbkafka.dto.Measurement;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, Measurement> kafkaTemplate;

    public void send(String message){
        log.info("Payload: {}", message);
       // kafkaTemplate.send(topicName, message);
    }

    public void sendData(Measurement measurement){
        log.info("Payload: {}", measurement.toString());
        kafkaTemplate.send(topicName, measurement);
    }



}
