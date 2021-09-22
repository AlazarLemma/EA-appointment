package edu.miu.group3.appointment.system.integration.Job;

import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.integration.email.EmailService;
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

    private final ReservationService reservationService;

    private final EmailService emailService;

    @Value("${reminder.appointment.time.in.minutes}")
    private List<Integer> reminderMinutes;


    @Autowired
    public AppointmentSchedule(ReservationService reservationService, EmailService emailService) {
        this.emailService = emailService;
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
                emailService.sendAppointmentReminderMail(reservation, minutes);
            }
        }

    }

}
