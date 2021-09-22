package edu.miu.group3.appointment.system.integration.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void logToKafka(String message) {
        kafkaTemplate.send("appointment-system-log", message);
    }
}