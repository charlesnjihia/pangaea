package com.example.springboot.event;

import com.example.springboot.config.RabbitMQConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;

@Controller
public class EventController {

    private final RabbitTemplate rabbitTemplate;

    public DemoEventController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/publish/{topic}")
    public ResponseEntity<Void> publishEventMessage(@RequestBody String topic) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QK_EXAMPLE_QUEUE, topic);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
