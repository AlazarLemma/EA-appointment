package edu.miu.group3.appointment.system.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

}
