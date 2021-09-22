package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.Reservation;
import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.repository.AppointmentRepository;
import edu.miu.group3.appointment.system.repository.ReservationRepository;
import edu.miu.group3.appointment.system.repository.UserRepository;
import edu.miu.group3.appointment.system.service.exception.ReservationNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation, Long appointmentId, Long userId){
        User user = userRepository.findById(userId).get();
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        reservation.setAppointment(appointment);
        reservation.setUser(user);
        reservationRepository.save(reservation);
    }

    public Reservation getReservation(Long reservationId){
        if(!reservationRepository.existsById(reservationId)){
            throw new ReservationNotFoundException("Reservation with id" + reservationId + "does not exist");
        }
        return reservationRepository.findById(reservationId).get();
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

    public List<Reservation> getConfirmedReservations(LocalDateTime startT, LocalDateTime endTime) {
        return reservationRepository.findConfirmedReservationsByTime(startT, endTime);
    }
}
