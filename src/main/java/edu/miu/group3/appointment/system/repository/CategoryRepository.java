package edu.miu.group3.appointment.system.repository;

import edu.miu.group3.appointment.system.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
