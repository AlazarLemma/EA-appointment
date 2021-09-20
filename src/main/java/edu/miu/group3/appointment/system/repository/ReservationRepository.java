package edu.miu.group3.appointment.system.repository;

import edu.miu.group3.appointment.system.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
}
