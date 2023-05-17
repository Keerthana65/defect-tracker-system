package com.defect.tracker.repositories;

import com.defect.tracker.entities.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DefectRepository extends JpaRepository<Defect,Long> , QuerydslPredicateExecutor<Defect> {
    public boolean existsByNameIgnoreCase(String name);
    public boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);
}
