package com.example.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * kafka生产者
 *
 * @author lz
 * @date 2019/10/11
 */
@Component
@Slf4j
@EnableScheduling
public class KafkaSender {


    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "00/5 * * * * ?")
    public void send() {
        Person person = new Person();
        person.setId(1);
        person.setName("张三");
        kafkaTemplate.send("aaa", JSONObject.toJSONString(person));
    }
}
