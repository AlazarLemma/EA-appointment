package edu.miu.group3.appointment.system.repository;

import edu.miu.group3.appointment.system.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment AS a WHERE a.appointmentTime < :endT and a.appointmentTime >= :startT")
    List<Appointment> findByAppointmentTime(LocalDateTime startT, LocalDateTime endT);
}
