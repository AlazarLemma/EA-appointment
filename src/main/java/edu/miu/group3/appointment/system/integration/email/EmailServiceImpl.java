package edu.miu.group3.appointment.system.integration.email;

import edu.miu.group3.appointment.system.domain.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Component
@Transactional
public class EmailServiceImpl implements EmailService{

    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class.getSimpleName());

    private final JavaMailSender mailSender;

    private final EmailRepository emailRepository;

    private final JmsTemplate jmsTemplate;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, EmailRepository emailRepository, JmsTemplate jmsTemplate) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "mailbox", containerFactory = "mailboxFactory")
    public void sendEmail(EmailTemplate emailTemplate) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(emailTemplate.getSubject());
            mimeMessageHelper.setFrom(emailTemplate.getFrom());
            mimeMessageHelper.setTo(emailTemplate.getTo());
            mimeMessageHelper.setText(emailTemplate.getContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());

            emailTemplate.setStatus(EmailStatus.SUCCESS);

            emailRepository.save(emailTemplate);

            logger.info("sending email from " + emailTemplate.getFrom() + " to " + emailTemplate.getTo() + " body: " + emailTemplate.getContent());

        } catch (MessagingException e) {
            emailTemplate.setStatus(EmailStatus.FAILED);
            emailRepository.save(emailTemplate);

            logger.error(e.getLocalizedMessage());
        }

    }

    @Override
    public void sendReservationConfirmationMail(Reservation reservation) {
        String emailBody = "Your reservation has been confirmed";

        EmailTemplate emailTemplate = new EmailTemplate(EmailType.RESERVATION_CONFIRMATION.getDefaultSubject(),
                senderEmail, reservation.getClient().getUsername(),
                emailBody,
                EmailType.APPOINTMENT_REMINDER);

        logger.info("added reminder to the queue");

        jmsTemplate.convertAndSend("mailbox", emailTemplate);
    }

    @Override
    public void sendReservationCancelMail(Reservation reservation) {
        String emailBody = "Your reservation has been canceled";
        EmailTemplate emailTemplate = new EmailTemplate(EmailType.RESERVATION_CANCELED.getDefaultSubject(),
                senderEmail, reservation.getClient().getUsername(),
                emailBody,
                EmailType.APPOINTMENT_REMINDER);

        jmsTemplate.convertAndSend("mailbox", emailTemplate);
    }

    @Override
    public void sendAppointmentReminderMail(Reservation reservation, int minutes) {
        String emailBody;

        if(minutes > 60){
            emailBody = Math.floor(minutes/60)+ " hours remaining for your appointment";
        } else{
            emailBody = minutes +" minute remaining for your appointment";
        }

        EmailTemplate emailTemplate = new EmailTemplate(EmailType.APPOINTMENT_REMINDER.getDefaultSubject(), senderEmail, reservation.getClient().getUsername(), emailBody, EmailType.APPOINTMENT_REMINDER);

        logger.info("added reminder to the queue");

        jmsTemplate.convertAndSend("mailbox", emailTemplate);
    }
}
