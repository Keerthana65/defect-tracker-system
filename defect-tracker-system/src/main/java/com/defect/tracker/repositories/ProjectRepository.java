package com.defect.tracker.repositories;

import com.defect.tracker.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProjectRepository extends JpaRepository<Project,Long>, QuerydslPredicateExecutor<Project> {
    public boolean existsByNameIgnoreCase(String name);

    public boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);


}
