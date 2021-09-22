package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment addAppointment(Appointment appointment, Long userId, Long categoryId);
    Appointment getAppointment(Long appointmentId);
    void updateAppointment(Long appointmentId, Appointment appointment);
    void deleteAppointment(Long appointmentId);

    List<Appointment> getByAppointmentTime(LocalDateTime startT, LocalDateTime endTime);
}
