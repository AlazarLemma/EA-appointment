package edu.miu.group3.appointment.system.repository;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    @Query("SELECT r FROM Reservation  r inner join Appointment a on r.appointment = a WHERE r.reservationStatus = 'ACCEPTED' and a.appointmentTime < :endT and a.appointmentTime >= :startT")
    List<Reservation> findConfirmedReservationsByTime(LocalDateTime startT, LocalDateTime endT);

    @Query("select r from Reservation r where r.reservationStatus = :status and r.appointment = :appointment and r.id = :reservationId")
    Reservation findByIdAppointmentAndStatus(Long reservationId, Appointment appointment, ReservationStatus status);

    @Query("select r from Reservation r where r.id = :reservationId and r.appointment = :appointment")
    Reservation findByIdAndAppointment(Long reservationId, Appointment appointment);
}
