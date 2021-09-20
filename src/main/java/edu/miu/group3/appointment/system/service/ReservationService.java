package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.repository.ReservationRepository;
import edu.miu.group3.appointment.system.service.exception.ReservationNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

    public void updateReservation(Long reservationId, Reservation reservation){
        if(!reservationRepository.existsById(reservationId)){
            throw new ReservationNotFoundException("Reservation with id" + reservationId + "does not exist");
        }
        Optional<Reservation> existingReservation = reservationRepository.findById(reservationId);
        existingReservation.map(r -> {
            r.setReservationTime(reservation.getReservationTime());
            r.setReservationStatus(reservation.getReservationStatus());
            return reservationRepository.save(r);
        });
    }

    public void deleteReservation(Long reservationId){
        if(!reservationRepository.existsById(reservationId)){
            throw new ReservationNotFoundException("Reservation with id" + reservationId + " does not exist");
        }
        reservationRepository.deleteById(reservationId);
    }
}
