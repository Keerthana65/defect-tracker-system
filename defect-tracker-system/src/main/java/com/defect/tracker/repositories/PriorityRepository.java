package com.defect.tracker.repositories;

import com.defect.tracker.entities.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority,Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByColorIgnoreCase(String color);

    boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);

    boolean existsByColorIgnoreCaseAndIdNot(String color,Long id);
}
