package edu.miu.group3.appointment.system.integration.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailTemplate, Long> {
}
