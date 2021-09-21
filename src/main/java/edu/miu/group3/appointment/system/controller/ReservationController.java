package edu.miu.group3.appointment.system.controller;

import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path= "api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservations();
    }

    @GetMapping(path = "{reservationId}")
    public Reservation getReservation(@PathVariable("reservationId") Long reservationId){
        return reservationService.getReservation(reservationId);
    }

    @PostMapping("/{userId}/{appointmentId}")
    public void addReservation(@PathVariable("userId") Long userId,
                               @PathVariable("appointmentId")Long appointmentId,
                               @Valid @RequestBody Reservation reservation){
        reservationService.addReservation(reservation, appointmentId, userId);
    }

    @DeleteMapping(path = "{reservationId}")
    public void deleteReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
    }
}
