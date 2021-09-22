package edu.miu.group3.appointment.system.repository;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByProvider(User provider);

    @Query("select a from Appointment a where a.provider = :provider and a.id= :appointmentId")
    Appointment findByIdAndProvider(Long appointmentId, User provider);
}
