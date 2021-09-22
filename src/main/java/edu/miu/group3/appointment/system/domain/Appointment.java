package edu.miu.group3.appointment.system.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Appointment")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "time")
    private LocalDateTime appointmentTime;

    @OneToMany
    private Set<Reservation> reservations = new HashSet<>();

<<<<<<< HEAD
    @ManyToOne
    private User user;
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private User provider;
>>>>>>> 49a0df5 (created modified repo and service layer for appointment, modified the email service and contextualized the appointment controller)

    @ManyToOne
    private Category category;

}
