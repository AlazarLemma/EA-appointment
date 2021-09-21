package edu.miu.group3.appointment.system.integration.email;

import edu.miu.group3.appointment.system.domain.AppointmentReminder;

public interface EmailService {

    void sendEmail(AppointmentReminder appointmentReminder);
}
