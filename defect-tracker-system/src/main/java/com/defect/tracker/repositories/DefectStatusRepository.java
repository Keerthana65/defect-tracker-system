package com.defect.tracker.repositories;

import com.defect.tracker.entities.DefectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DefectStatusRepository extends JpaRepository<DefectStatus,Long> , QuerydslPredicateExecutor<DefectStatus> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndId(String name,Long Id);
}
