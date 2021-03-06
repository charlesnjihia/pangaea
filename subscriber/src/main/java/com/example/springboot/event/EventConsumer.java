package com.example.springboot.event;

import com.example.springboot.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventConsumer {

    @RabbitListener(queues = RabbitMQConfig.QK_EXAMPLE_QUEUE)    
    public void onMessageReceived(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Message received!: " + message);

        try{
            channel.basicAck(tag, false);
        }catch (Exception e) {
            channel.basicNack(tag, false, true);
        }
    }
}
