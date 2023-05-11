package com.defect.tracker.repositories;

import com.defect.tracker.entities.ProjectAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAllocationRepository extends JpaRepository<ProjectAllocation,Long> {
}
