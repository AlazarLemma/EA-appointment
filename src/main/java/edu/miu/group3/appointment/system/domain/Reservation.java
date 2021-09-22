package edu.miu.group3.appointment.system.domain;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus reservationStatus;

    @ManyToOne
    private Appointment appointment;

<<<<<<< HEAD
    @ManyToOne
    private User user;
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private User client;
>>>>>>> 49a0df5 (created modified repo and service layer for appointment, modified the email service and contextualized the appointment controller)
}
