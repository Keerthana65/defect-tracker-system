package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Employee;
import com.defect.tracker.response.dto.EmployeeResponse;
import com.defect.tracker.resquest.dto.EmployeeRequest;
import com.defect.tracker.search.dto.EmployeeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    public void saveEmployee(EmployeeRequest employeeRequest);

    public boolean existsByEmployeeEmail(String email);

    public boolean existsByPhoneNumber(Long phoneNumber);

    public boolean existsById(Long id);

    public List<EmployeeResponse> getAllEmployee();

    public EmployeeResponse getEmployeeById(Long id);
    public Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize, String sortproperty);

    public void deleteEmployee(Long id);

    public boolean isUpdateEmployeeEmailALreadyExists(String email,Long id);

    public boolean isUpdateEmployeePhonenumbeAlreadyExists(Long phoneNumber,Long id);

    public List<EmployeeResponse> multiSearchEmployee(Pageable pageable, PaginatedContentResponse.Pagination pagination, EmployeeSearch employeeSearch);


}
