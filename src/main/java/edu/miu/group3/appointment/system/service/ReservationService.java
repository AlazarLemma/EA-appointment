package edu.miu.group3.appointment.system.service;


import edu.miu.group3.appointment.system.domain.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    void addReservation(Reservation reservation, Long appointmentId, Long userId);
    Reservation getReservation(Long reservationId);
    void updateReservation(Long reservationId, Reservation reservation);
    void deleteReservation(Long reservationId);
    List<Reservation> getConfirmedReservations(LocalDateTime startT, LocalDateTime endTime);
}
