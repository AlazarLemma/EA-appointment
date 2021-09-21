package edu.miu.group3.appointment.system.integration.Job;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.AppointmentReminder;
import edu.miu.group3.appointment.system.integration.email.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AppointmentSchedule {

    private final Logger logger = LoggerFactory.getLogger(AppointmentSchedule.class.getSimpleName());

    private final JmsTemplate jmsTemplate;

   // private final AppointmentService appointmentService;

    @Value("${reminder.appointment.time.in.minutes}")
    private List<Integer> reminderMinutes;

    @Value("${reminder.appointment.sender.email}")
    private String senderEmail;

    public AppointmentSchedule(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Scheduled(fixedDelayString = "${reminder.scheduler.fixedDelay.in.millisecond}")
    public void scheduleToSendAppointmentReminder() {

//        List<Appointment> appointments = appointmentService.getByRemainingTime();

        for(Integer h : reminderMinutes){
            logger.info(h.toString());
            sendAppointmentReminder(new Appointment(), h);
        }

//        List<Appointment> appointments = appointmentService.getByRemainingTime()
        //get send email
        //publish to active mq

        //appointmentService.getAppointmentsWithIn(20);

//        List<Appointment> appointmentList = new ArrayList<>();
//
//        for(Appointment appointment: appointmentList){
//           sendAppointmentReminder(appointment, min);
//
//        }
    }

    private void sendAppointmentReminder(Appointment appointment, int minutes){
        String emailBody = minutes + " minutes remaining for your appointment";

        //EmailTemplate emailTemplate = new EmailTemplate(senderEmail, appointment.getUser().getEmail(), emailBody);
        EmailTemplate emailTemplate = new EmailTemplate(senderEmail, "user@email.com", emailBody);

        AppointmentReminder reminder = new AppointmentReminder(appointment, Status.PENDING, LocalDateTime.now(), emailTemplate, minutes);

        logger.info("added reminder to the queue");

        jmsTemplate.convertAndSend("mailbox", reminder);
    }
}
