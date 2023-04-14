package org.rza.mpei.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
/*
* Чтобы задать кастомный десериалайзер нужно в application.yaml
* в настройках producer указать value-deserializer: ru.mpei.dbkafka.util.CustomDeserializer
*
 * Для использования нашего DTO нужно в consumer нужно типизировать ключ и значение
 *
 * @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, Measurement> measurementPayload) {
    * Далее как в примере }
* */
public class CustomDeserializer
    implements Deserializer<Measurement> {
    @Override
    public Measurement deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, Measurement.class);
        } catch (IOException exception) {
            String message = new String(data, StandardCharsets.UTF_8);
            log.error("Unable to deserialize measurement: {}", message, exception);
            return null;
        }
    }
}
