package edu.miu.group3.appointment.system.controller;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.service.AppointmentServiceImpl;
import edu.miu.group3.appointment.system.service.UserService;
import edu.miu.group3.appointment.system.service.dto.AuthUserSubject;
import edu.miu.group3.appointment.system.service.util.CustomLoggerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path= "api/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentServiceImpl appointmentService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CustomLoggerService loggerService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        List<Appointment> result = appointmentService.getAllAppointments();
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "{appointmentId}")
    public Appointment getAppointment(@PathVariable("appointmentId") Long appointmentId){
        return appointmentService.getAppointment(appointmentId);
    }

    @PostMapping(path = "{categoryId}")
    public ResponseEntity<?> addAppointment(HttpServletRequest request,
                                            @PathVariable("categoryId") Long categoryId,
                                            @Valid @RequestBody Appointment appointment){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");
        User dbUser = userService.findByUUID(user.getUuid());
        if (dbUser == null) {
            loggerService.log("User not found " + user);
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }

        Appointment result = appointmentService.addAppointment(appointment, dbUser.getId(), categoryId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{appointmentId}")
    public void deleteAppointment(@PathVariable("appointmentId") Long appointmentId){
        appointmentService.deleteAppointment(appointmentId);
    }
}
