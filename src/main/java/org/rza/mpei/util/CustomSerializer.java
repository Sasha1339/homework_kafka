package org.rza.mpei.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
/*
 * Чтобы задать кастомный сериалйзер нужно в application.yaml
 * в настройках consumer указать value-serializer: ru.mpei.dbkafka.util.CustomSerializer
 *
 * Для использования нашего DTO в produser нужно в kafkaTemplate типизировать ключ и значение
 *
 * private final KafkaTemplate<String, Measurement> kafkaTemplate;
 *
 * Далее как в примере TopicProducer
 * Сам топик можно сделать самим через OffsetExplorer либо добавить настройку автосоздания топиков
 * */
public class CustomSerializer implements Serializer<Measurement> {
    @Override
    public byte[] serialize(String s, Measurement commandDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (commandDto != null) {
            try {
                return objectMapper.writeValueAsBytes(commandDto);
            } catch (JsonProcessingException e) {
                log.error("Unable to serialize measurement cause : {}", e.getMessage(), e);
                return new byte[0];
            }
        }
        return new byte[0];
    }
}
