package edu.miu.group3.appointment.system.integration.Job;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReminderServiceImpl implements ReminderService{
    @Value("${some-path-to-values}")
    private Long[] scheduleTimes;

    @Autowired
    AppointmentService appointmentService;


    @Override
    public void sendAppointmentReminders() {
        //find appointments that hasn't passed already and their status is confirmed
        //find the user for those appointments
        //check to see if there is no successful reminder already sent for that appointment
        //log the appointment and send email

    }

    private List<Appointment> getAppointmentToRemind(){
        //find all appointments whose time is 48 hrs left
        //find
        return new ArrayList<>();
    }
}
