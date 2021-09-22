package edu.miu.group3.appointment.system.repository;

import edu.miu.group3.appointment.system.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    @Query("SELECT r FROM Reservation  r inner join Appointment a on r.appointment = a WHERE r.reservationStatus = 'ACCEPTED' and a.appointmentTime < :endT and a.appointmentTime >= :startT")
    List<Reservation> findConfirmedReservationsByTime(LocalDateTime startT, LocalDateTime endT);
}
