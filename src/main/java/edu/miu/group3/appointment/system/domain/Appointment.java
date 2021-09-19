package edu.miu.group3.appointment.system.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Appointment")
public class Appointment {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "time")
    private LocalDateTime appointmentTime;

    @ManyToOne
    private Category category;

    @OneToMany
    private Set<Reservation> reservations = new HashSet<>();

}
