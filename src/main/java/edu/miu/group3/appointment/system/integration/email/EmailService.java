package edu.miu.group3.appointment.system.integration.email;

public interface EmailService {

    EmailResponse sendEmail(EmailRequest request);
}
