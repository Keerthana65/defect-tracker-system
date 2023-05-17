package com.defect.tracker.repositories;

import com.defect.tracker.entities.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReleaseRepository extends JpaRepository<Release,Long> , QuerydslPredicateExecutor<Release> {
    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
}
