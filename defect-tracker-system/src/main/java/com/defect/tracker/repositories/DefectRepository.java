package com.defect.tracker.repositories;

import com.defect.tracker.entities.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectRepository extends JpaRepository<Defect,Long> {
    public boolean existsByNameIgnoreCase(String name);
    public boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);
}
