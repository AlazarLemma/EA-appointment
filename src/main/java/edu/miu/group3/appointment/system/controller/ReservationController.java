package edu.miu.group3.appointment.system.controller;

import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.service.ReservationService;
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
@RequestMapping(path= "api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    private final UserService userService;
    @Autowired
    private final CustomLoggerService loggerService;

    @GetMapping
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservations();
    }

    @GetMapping(path = "{reservationId}")
    public Reservation getReservation(@PathVariable("reservationId") Long reservationId){
        return reservationService.getReservation(reservationId);
    }

    @PostMapping("{appointmentId}")
    public ResponseEntity<?> addReservation(HttpServletRequest request,
                               @PathVariable("appointmentId")Long appointmentId,
                               @Valid @RequestBody Reservation reservation){
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");
//        System.out.println("test" + user.getUuid());
//        System.out.println("test2" + userService.findByUUID(user.getUuid()));
        User dbUser = userService.findByUUID(user.getUuid());
        if (dbUser == null) {
            loggerService.log("User not found " + user);
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        Reservation result = reservationService.addReservation(reservation, appointmentId, dbUser.getId());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{reservationId}")
    public void deleteReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
    }
}
