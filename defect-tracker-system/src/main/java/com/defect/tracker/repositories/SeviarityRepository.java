package com.defect.tracker.repositories;

import com.defect.tracker.entities.Seviarity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeviarityRepository extends JpaRepository<Seviarity,Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByColorIgnoreCase(String color);

   boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);

   boolean existsByColorIgnoreCaseAndIdNot(String color,Long id);
}
