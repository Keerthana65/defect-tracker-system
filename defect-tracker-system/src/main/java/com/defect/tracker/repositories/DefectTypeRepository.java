package com.defect.tracker.repositories;

import com.defect.tracker.entities.DefectType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectTypeRepository extends JpaRepository<DefectType,Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndId(String name,Long Id);

}
