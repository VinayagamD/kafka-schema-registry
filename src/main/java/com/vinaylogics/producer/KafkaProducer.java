package com.vinaylogics.producer;

import com.vinaylogics.dto.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Employee> template;

    @Value("${topic.name}")
    private String topicName;

    public void send(Employee employee){
        CompletableFuture<SendResult<String,Employee>> future =template.send(topicName, UUID.randomUUID().toString(),employee);
        future.whenComplete((result, ex) -> {
            if(ex == null){
                log.info(
                        "Sent message = [" + employee +
                                "] with offset=[" + result.getRecordMetadata().offset()+"]"
                );
            } else {
                log.error(
                        "Unable to send message=[" + employee + "] due to : " + ex.getMessage()
                );
            }
        });
    }
}
