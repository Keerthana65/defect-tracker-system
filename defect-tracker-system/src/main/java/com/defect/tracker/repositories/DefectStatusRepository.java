package com.defect.tracker.repositories;

import com.defect.tracker.entities.DefectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectStatusRepository extends JpaRepository<DefectStatus,Long> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndId(String name,Long Id);
}
