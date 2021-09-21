package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.repository.AppointmentRepository;
import edu.miu.group3.appointment.system.service.exception.AppointmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public void addAppointment(Appointment appointment){
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

    @Override
    public List<Appointment> getByAppointmentTime(LocalDateTime startT, LocalDateTime endTime) {
        return appointmentRepository.findByAppointmentTime(startT, endTime);
    }
}
