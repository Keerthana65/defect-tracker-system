package com.defect.tracker.repositories;

import com.defect.tracker.entities.Severity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SeverityRepository extends JpaRepository<Severity,Long>, QuerydslPredicateExecutor<Severity> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByColorIgnoreCase(String color);

   boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);

   boolean existsByColorIgnoreCaseAndIdNot(String color,Long id);
}
