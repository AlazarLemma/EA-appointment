package edu.miu.group3.appointment.system.integration.email;

import edu.miu.group3.appointment.system.domain.AppointmentReminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService{

    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class.getSimpleName());
    //appointmentLogService

    @Override
    @JmsListener(destination = "mailbox", containerFactory = "mailboxFactory")
    public void sendEmail(AppointmentReminder appointmentReminder) {

        EmailTemplate emailTemplate = appointmentReminder.getEmailTemplate();

        logger.info("sending email from " + emailTemplate.getFrom() + " to " + emailTemplate.getTo() + " body: " + emailTemplate.getBody());

    }
}
