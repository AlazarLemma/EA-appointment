package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.domain.User;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    List<Appointment> getAllAppointmentsForProvider(User provider);
    Appointment addAppointment(Appointment appointment, Long userId, Long categoryId);
    Appointment getAppointment(Long appointmentId);
    Appointment getAppointment(User user, Long appointmentId);
    void updateAppointment(Long appointmentId, Appointment appointment);
    void deleteAppointment(User provider, Long appointmentId);

    Reservation confirmReservation(User provider, Long appointmentId, Long reservationId);

    void deleteAppointment(Appointment appointment);
}
