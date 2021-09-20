package edu.miu.group3.appointment.system.integration.Job;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(AppointmentReminder email) {
        System.out.println("Received <" + email + ">");
    }
}
