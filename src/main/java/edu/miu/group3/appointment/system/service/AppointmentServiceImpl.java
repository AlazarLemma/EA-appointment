package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.repository.AppointmentRepository;
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

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public void addAppointment(Appointment appointment, Long userId){
        User user = userRepository.findById(userId).get();
        appointment.setUser(user);
        appointmentRepository.save(appointment);
    }

    public Appointment getAppointment(Long appointmentId){
        if(!appointmentRepository.existsById(appointmentId)){
            throw new AppointmentNotFoundException("Appointment with id" + appointmentId + "does not exist");
        }
        return appointmentRepository.findById(appointmentId).get();
    }

    public void updateAppointment(Long appointmentId, Appointment appointment){
        if(!appointmentRepository.existsById(appointmentId)){
            throw new AppointmentNotFoundException("Appointment with id" + appointmentId + "does not exist");
        }
        Optional<Appointment> current = appointmentRepository.findById(appointmentId);
        //current.map(r -> {});
    }

    public void deleteAppointment(Long appointmentId){
        if(!appointmentRepository.existsById(appointmentId)){
            throw new AppointmentNotFoundException("Appointment with id" + appointmentId + " does not exist");
        }
        appointmentRepository.deleteById(appointmentId);
    }
}
