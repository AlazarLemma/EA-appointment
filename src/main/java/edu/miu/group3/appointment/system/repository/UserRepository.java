package edu.miu.group3.appointment.system.repository;

import edu.miu.group3.appointment.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
