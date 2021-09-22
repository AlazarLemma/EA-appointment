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
    private Status reservationStatus;

    @ManyToOne
    private Appointment appointment;

    @ManyToOne
    private User user;
}
