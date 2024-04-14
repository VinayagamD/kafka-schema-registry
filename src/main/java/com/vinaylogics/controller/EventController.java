package com.vinaylogics.controller;

import com.vinaylogics.dto.Employee;
import com.vinaylogics.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final KafkaProducer producer;

    @PostMapping("/event")
    public String sendMessage(@RequestBody Employee employee){
        producer.send(employee);
        return "message published !";
    }
}
