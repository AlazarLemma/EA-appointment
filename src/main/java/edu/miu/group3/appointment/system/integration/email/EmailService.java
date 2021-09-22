package edu.miu.group3.appointment.system.integration.email;

import edu.miu.group3.appointment.system.domain.Reservation;

public interface EmailService {
    void sendReservationConfirmationMail(Reservation reservation);
    void sendReservationCancelMail(Reservation r);
    void sendAppointmentReminderMail(Reservation reservation, int minute);
}
