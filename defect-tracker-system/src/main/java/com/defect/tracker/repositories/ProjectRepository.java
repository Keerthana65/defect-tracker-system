package com.defect.tracker.repositories;

import com.defect.tracker.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    public boolean existsByNameIgnoreCase(String name);

    public boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);


}
