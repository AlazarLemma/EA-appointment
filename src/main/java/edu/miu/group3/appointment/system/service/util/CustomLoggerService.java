package edu.miu.group3.appointment.system.service.util;

import edu.miu.group3.appointment.system.integration.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomLoggerService {
    @Autowired
    private KafkaSender sender;
    public void log(String message) {
        sender.logToKafka(message);
    }
}
