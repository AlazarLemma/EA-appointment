package edu.miu.group3.appointment.system.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    private Set<Appointment> appointments = new HashSet<>();
}
