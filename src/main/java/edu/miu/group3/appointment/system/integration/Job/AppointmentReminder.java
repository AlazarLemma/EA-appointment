package edu.miu.group3.appointment.system.integration.Job;

import edu.miu.group3.appointment.system.domain.Appointment;
import edu.miu.group3.appointment.system.integration.email.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentReminder {

    private Long Id;

    private String email;

    private Appointment appointment;

    private Status status;

    private LocalDateTime createdTime;

    private EmailRequest sentEmail;

    private Integer remainTimeInMins;
}
