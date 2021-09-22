package edu.miu.group3.appointment.system.service;


import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.domain.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Reservation addReservation(Reservation reservation, Long appointmentId, Long userId);
    Reservation getReservation(Long reservationId);
    void updateReservation(Long reservationId, Reservation reservation);
    void deleteReservation(Long reservationId);
    List<Reservation> getConfirmedReservations(LocalDateTime startT, LocalDateTime endTime);

    Reservation getAppointmentReservation(Appointment appointment, Long reservationId, ReservationStatus status);

    Reservation getAppointmentReservation(Appointment appointment, Long reservationId);
}
