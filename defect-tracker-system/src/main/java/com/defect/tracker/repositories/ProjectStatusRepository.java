package com.defect.tracker.repositories;

import com.defect.tracker.entities.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus,Long> , QuerydslPredicateExecutor<ProjectStatus> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
}
