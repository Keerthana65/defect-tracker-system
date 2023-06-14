package com.defect.tracker.repositories;

import com.defect.tracker.entities.DefectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DefectTypeRepository extends JpaRepository<DefectType,Long>, QuerydslPredicateExecutor<DefectType> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndId(String name,Long Id);

}
