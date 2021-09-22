package edu.miu.group3.appointment.system.controller;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.service.AppointmentService;
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
import java.util.Set;

@RestController
@RequestMapping(path= "api/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CustomLoggerService loggerService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments(HttpServletRequest request){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");
        User provider = userService.findByUUID(user.getUuid());

        List<Appointment> result = appointmentService.getAllAppointmentsForProvider(provider);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "{appointmentId}")
    public Appointment getAppointment(HttpServletRequest request, @PathVariable("appointmentId") Long appointmentId){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");
        User provider = userService.findByUUID(user.getUuid());

        return appointmentService.getAppointment(provider, appointmentId);
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
    public void deleteAppointment(HttpServletRequest request, @PathVariable("appointmentId") Long appointmentId){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");
        User provider = userService.findByUUID(user.getUuid());
        Appointment appointment = appointmentService.getAppointment(provider, appointmentId);
        appointmentService.deleteAppointment(appointment);
    }

    @GetMapping(path = "{appointmentId}/reservation")
    public ResponseEntity<Set<Reservation>> getAppointmentReservations(HttpServletRequest request, @PathVariable("appointmentId") Long appointmentId){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");

        User provider = userService.findByUUID(user.getUuid());

        Appointment appointment = appointmentService.getAppointment(provider, appointmentId);

        return new ResponseEntity<>(appointment.getReservations(), HttpStatus.OK);
    }

    @PatchMapping(path = "{appointmentId}/reservation/{reservationId}")
    public ResponseEntity<Set<Reservation>> getAppointmentReservations(HttpServletRequest request, @PathVariable("appointmentId") Long appointmentId, @PathVariable("reservationId") Long reservationId){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");

        User provider = userService.findByUUID(user.getUuid());

        Appointment appointment = appointmentService.getAppointment(provider, appointmentId);

        return new ResponseEntity<>(appointment.getReservations(), HttpStatus.OK);
    }

    @PostMapping(path = "{appointmentId}/reservation/{reservationId}/confirm")
    public ResponseEntity<Reservation> confirmReservation(HttpServletRequest request, @PathVariable("appointmentId") Long appointmentId, @PathVariable("reservationId") Long reservationId){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");

        User provider = userService.findByUUID(user.getUuid());

        Reservation reservation = appointmentService.confirmReservation(provider, appointmentId, reservationId);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }



}
