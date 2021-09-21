package edu.miu.group3.appointment.system.integration.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
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

    private JavaMailSender mailSender;

    private EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    @Override
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
}
