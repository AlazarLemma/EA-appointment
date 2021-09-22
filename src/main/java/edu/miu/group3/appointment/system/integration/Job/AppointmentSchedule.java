package edu.miu.group3.appointment.system.integration.Job;

import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.integration.email.EmailTemplate;
import edu.miu.group3.appointment.system.integration.email.Type;
import edu.miu.group3.appointment.system.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class AppointmentSchedule {

    private final Logger logger = LoggerFactory.getLogger(AppointmentSchedule.class.getSimpleName());

    private final JmsTemplate jmsTemplate;

    private final ReservationService reservationService;

    @Value("${reminder.appointment.time.in.minutes}")
    private List<Integer> reminderMinutes;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Autowired
    public AppointmentSchedule(JmsTemplate jmsTemplate, ReservationService reservationService) {
        this.jmsTemplate = jmsTemplate;
        this.reservationService = reservationService;
    }


    @Scheduled(fixedDelayString = "${reminder.scheduler.fixedDelay.in.millisecond}")
    public void scheduleToSendAppointmentReminder() {

        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        for(Integer minutes : reminderMinutes){

            LocalDateTime startT = currentTime.plusMinutes(minutes);
            LocalDateTime endT = startT.plusMinutes(1);

            List<Reservation> reservations = reservationService.getConfirmedReservations(startT, endT);

            for(Reservation reservation: reservations){
                sendAppointmentReminder(reservation, minutes);
            }
        }

    }

    private void sendAppointmentReminder(Reservation reservation, int minutes){
        String emailBody = "";

        if(minutes > 60){
            emailBody = Math.floor(minutes/60)+ " hours remaining for your appointment";
        } else{
            emailBody = minutes +" minute remaining for your appointment";
        }

        EmailTemplate emailTemplate = new EmailTemplate(Type.APPOINTMENT_REMINDER.getDefaultSubject(), senderEmail, reservation.getUser().getUsername(), emailBody, Type.APPOINTMENT_REMINDER);

        logger.info("added reminder to the queue");

        jmsTemplate.convertAndSend("mailbox", emailTemplate);
    }
}
