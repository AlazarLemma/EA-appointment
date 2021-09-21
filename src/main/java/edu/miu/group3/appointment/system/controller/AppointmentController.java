package edu.miu.group3.appointment.system.controller;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.service.AppointmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path= "api/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentServiceImpl appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping(path = "{appointmentId}")
    public Appointment getAppointment(@PathVariable("appointmentId") Long appointmentId){
        return appointmentService.getAppointment(appointmentId);
    }

    @PostMapping
    public void addAppointment(@Valid @RequestBody Appointment appointment){
        appointmentService.addAppointment(appointment);
    }

    @DeleteMapping(path = "{appointmentId}")
    public void deleteAppointment(@PathVariable("appointmentId") Long appointmentId){
        appointmentService.deleteAppointment(appointmentId);
    }
}
