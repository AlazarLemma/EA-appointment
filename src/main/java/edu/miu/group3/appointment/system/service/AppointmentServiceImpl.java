package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.*;
import edu.miu.group3.appointment.system.integration.email.EmailService;
import edu.miu.group3.appointment.system.repository.AppointmentRepository;
import edu.miu.group3.appointment.system.repository.CategoryRepository;
import edu.miu.group3.appointment.system.repository.UserRepository;
import edu.miu.group3.appointment.system.service.exception.AppointmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ReservationService reservationService;
    private final EmailService emailService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  UserRepository userRepository,
                                  CategoryRepository categoryRepository, ReservationService reservationService, EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.reservationService = reservationService;
        this.emailService = emailService;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAllAppointmentsForProvider(User provider) {
        return appointmentRepository.findAllByProvider(provider);
    }

    public Appointment addAppointment(Appointment appointment, Long userId, Long categoryId){
        if(userRepository.existsById(userId)){
            User user = userRepository.findById(userId).get();
            appointment.setProvider(user);
        }

        if(categoryRepository.existsById(categoryId)){
            Category category = categoryRepository.findById(categoryId).get();
            appointment.setCategory(category);
        }
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointment(Long appointmentId){
        if(!appointmentRepository.existsById(appointmentId)){
            throw new AppointmentNotFoundException("Appointment with id" + appointmentId + "does not exist");
        }
        return appointmentRepository.findById(appointmentId).get();
    }

    @Override
    public Appointment getAppointment(User provider, Long appointmentId) {
        return appointmentRepository.findByIdAndProvider(appointmentId, provider);
    }

    public void updateAppointment(Long appointmentId, Appointment appointment){
        if(!appointmentRepository.existsById(appointmentId)){
            throw new AppointmentNotFoundException("Appointment with id" + appointmentId + "does not exist");
        }
        Optional<Appointment> current = appointmentRepository.findById(appointmentId);
        //current.map(r -> {});
    }

    public void deleteAppointment(User provider, Long appointmentId){
        if(!appointmentRepository.existsById(appointmentId)){
            throw new AppointmentNotFoundException("Appointment with id" + appointmentId + " does not exist");
        }

        appointmentRepository.deleteById(appointmentId);
    }

    public void deleteAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    public Reservation confirmReservation(User provider, Long appointmentId, Long reservationId) {
        Appointment appointment = getAppointment(provider, appointmentId);

        //accept the new one
        Reservation reservation = reservationService.getAppointmentReservation(appointment, reservationId);
        if(reservation != null){
            reservationService.updateReservation(reservationId, reservation);
            emailService.sendReservationConfirmationMail(reservation);
        }
        
        //cancel and old one
        Reservation oldReservation = reservationService.getAppointmentReservation(appointment, reservationId, ReservationStatus.ACCEPTED);

        if(oldReservation != null){
            reservationService.updateReservation(oldReservation.getId(), oldReservation);
            emailService.sendReservationCancelMail(oldReservation);
        }

        return reservation;
    }
}
