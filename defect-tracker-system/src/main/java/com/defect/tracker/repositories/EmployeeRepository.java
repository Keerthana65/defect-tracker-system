package com.defect.tracker.repositories;

import com.defect.tracker.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EmployeeRepository extends JpaRepository<Employee,Long> , QuerydslPredicateExecutor<Employee> {

    public boolean existsByEmailIgnoreCase(String email);

    public boolean existsByPhoneNumber(Long phoneNumber);

    public boolean existsByEmailIgnoreCaseAndIdNot(String email,Long id);

    public boolean existsByPhoneNumberAndIdNot(Long phoneNumber,Long id);

}
