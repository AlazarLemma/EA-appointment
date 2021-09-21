package edu.miu.group3.appointment.system.integration.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.group3.appointment.system.domain.events.UserRegisteredEvent;
import edu.miu.group3.appointment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {
    @Autowired
    private UserService service;

    @KafkaListener(topics = {"user-registered"})
    public void receieveRegisteredUser(@Payload String message) {
        System.out.println("Receiver received user registered = " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserRegisteredEvent event = objectMapper.readValue(message, UserRegisteredEvent.class);
            service.handleUserRegistered(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
