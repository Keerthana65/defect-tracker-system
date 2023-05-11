package com.defect.tracker.repositories;

import com.defect.tracker.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public boolean existsByEmailIgnoreCase(String email);

    public boolean existsByPhoneNumber(Long phoneNumber);

    public boolean existsByEmailIgnoreCaseAndIdNot(String email,Long id);

    public boolean existsByPhoneNumberAndIdNot(Long phoneNumber,Long id);

}
