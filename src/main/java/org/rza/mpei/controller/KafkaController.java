package org.rza.mpei.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mpei.dbkafka.dto.Measurement;
import ru.mpei.dbkafka.produser.TopicProducer;
import ru.mpei.dbkafka.repositories.RepositoryDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
@RestController
public class KafkaController {

    @Autowired
    private RepositoryDto repositoryDto;
    private final TopicProducer topicProducer;
    
    @GetMapping("/send")
    public void send(){
        topicProducer.send("test message");
    }

    @PostMapping("/data")
    public Measurement sendData(@RequestBody Measurement measurement){
        topicProducer.sendData(measurement);
        return measurement;
    }

    @GetMapping("/get")
    public List<Measurement> get(){
      return   repositoryDto.findAll();
    }

}
