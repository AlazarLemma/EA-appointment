package edu.miu.group3.appointment.system.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @NonNull
    @Column(nullable = false)
    private Integer defaultSessionLength;

    @OneToMany
    private Set<Appointment> appointments = new HashSet<>();


    @PrePersist
    public void prePersist() {
        if (this.defaultSessionLength == null || this.defaultSessionLength == 0 ) {
            this.defaultSessionLength = 30;
        }
    }
}
