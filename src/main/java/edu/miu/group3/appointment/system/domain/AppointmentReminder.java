package edu.miu.group3.appointment.system.domain;

import edu.miu.group3.appointment.system.integration.email.EmailTemplate;
import edu.miu.group3.appointment.system.integration.Job.Status;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppointmentReminder implements Serializable{

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    private Appointment appointment;

    private Status status;

    private LocalDateTime createdTime;

    private EmailTemplate emailTemplate;

    private Integer remainTimeInMinute;

    public AppointmentReminder(Appointment appointment, Status status, LocalDateTime createdTime, EmailTemplate sentEmail, Integer remainTimeInMinute){

        this.appointment = appointment;
        this.status = status;
        this.createdTime = createdTime;
        this.emailTemplate = sentEmail;
        this.remainTimeInMinute = remainTimeInMinute;
    }
}
