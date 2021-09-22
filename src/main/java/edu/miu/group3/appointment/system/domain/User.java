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

    @OneToMany
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany
    private Set<Reservation> reservations = new HashSet<>();

    @NonNull
    @ElementCollection
    private List<String> roles;
}
