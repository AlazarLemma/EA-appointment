package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    void addAppointment(Appointment appointment, Long userId, Long categoryId);
    Appointment getAppointment(Long appointmentId);
    void updateAppointment(Long appointmentId, Appointment appointment);
    void deleteAppointment(Long appointmentId);
}
