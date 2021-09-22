package edu.miu.group3.appointment.system.domain;

import lombok.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @Column(unique = true)
    private String username;
    
    @NonNull
    @Column(unique = true)
    private String uuid;

    @NonNull
    private boolean active;

<<<<<<< HEAD
    @OneToMany
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany
=======
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "provider")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
>>>>>>> 49a0df5 (created modified repo and service layer for appointment, modified the email service and contextualized the appointment controller)
    private Set<Reservation> reservations = new HashSet<>();

    @NonNull
    @ElementCollection
    private List<String> roles;
}
