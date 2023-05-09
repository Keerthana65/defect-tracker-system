package com.defect.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.defect.tracker.entities.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {
  boolean existsByNameIgnoreCase(String name);

  boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
}
