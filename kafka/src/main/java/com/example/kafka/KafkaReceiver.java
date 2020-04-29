package com.example.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * kafka消费者
 *
 * @author lz
 * @date 2019/10/11
 */
@Slf4j
@Component
public class KafkaReceiver {
    @KafkaListener(topics = {"aaa"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object obj = kafkaMessage.get();
            JSONObject jsonObject = JSONObject.parseObject(obj.toString());
            Person person = jsonObject.toJavaObject(Person.class);
            log.error("kafka消费----------->" + person);
        }
    }
}
