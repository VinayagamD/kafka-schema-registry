package com.vinaylogics.consumer;


import com.vinaylogics.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${topic.name}")
    public void read(ConsumerRecord<String, Employee> consumerRecord) {
        String key = consumerRecord.key();
//        Employee employee = Employee.newBuilder(consumerRecord.value()).build();
        log.info("Avro message received for key : " + key + " value : " + consumerRecord.value() );
    }
}
