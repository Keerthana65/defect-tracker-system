package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Designation;
import com.defect.tracker.entities.Employee;
import com.defect.tracker.entities.QEmployee;
import com.defect.tracker.repositories.EmployeeRepository;
import com.defect.tracker.response.dto.EmployeeResponse;
import com.defect.tracker.resquest.dto.EmployeeRequest;
import com.defect.tracker.search.dto.EmployeeSearch;
import com.defect.tracker.service.EmployeeService;
import com.defect.tracker.utils.Utils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        Designation designation = new Designation();
        designation.setId(employeeRequest.getDesignationId());
        employee.setDesignation(designation);
        BeanUtils.copyProperties(employeeRequest, employee);
        employeeRepository.save(employee);
    }

    @Override
    public boolean existsByEmployeeEmail(String email) {
        return employeeRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public boolean existsByPhoneNumber(Long phoneNumber) {
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsById(Long id)
    {
        return  employeeRepository.existsById(id);
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<EmployeeResponse> employeeResponses=new ArrayList<>();
        List<Employee> employees=employeeRepository.findAll();
        for (Employee employee:employees
             ) {
            EmployeeResponse employeeResponse=new EmployeeResponse();
            employeeResponse.setDesignationName(employee.getDesignation().getName());
            BeanUtils.copyProperties(employee,employeeResponse);
            employeeResponses.add(employeeResponse);

        }
        return employeeResponses;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        EmployeeResponse employeeResponse=new EmployeeResponse();
        Employee employee=employeeRepository.findById(id).get();
        employeeResponse.setDesignationName(employee.getDesignation().getName());
        BeanUtils.copyProperties(employee,employeeResponse);
        return employeeResponse;
    }

    @Override
    public Page<Employee> getEmployeePagination(Integer pageNumber, Integer pageSize, String sortproperty) {
        Pageable pageable=null;
        if(sortproperty==null)
        {
            pageable=PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC,"name");
        }
        else {
            pageable=PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC,sortproperty);
        }
        return employeeRepository.findAll(pageable);
    }
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public boolean isUpdateEmployeeEmailALreadyExists(String email, Long id) {
        return employeeRepository.existsByEmailIgnoreCaseAndIdNot(email,id);
    }

    @Override
    public boolean isUpdateEmployeePhonenumbeAlreadyExists(Long phoneNumber, Long id) {
        return employeeRepository.existsByPhoneNumberAndIdNot(phoneNumber,id);
    }

    @Override
    public List<EmployeeResponse> multiSearchEmployee(Pageable pageable, PaginatedContentResponse.Pagination pagination, EmployeeSearch employeeSearch) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(employeeSearch.getEmployeeFirName()))
        {
            booleanBuilder.and(QEmployee.employee.firName.eq(employeeSearch.getEmployeeFirName()));
        }
        if(Utils.isNotNullAndEmpty(employeeSearch.getEmployeeLasName()))
        {
            booleanBuilder.and(QEmployee.employee.lasName.eq(employeeSearch.getEmployeeLasName()));
        }
        if(Utils.isNotNullAndEmpty(employeeSearch.getEmployeeEmail()))
        {
            booleanBuilder.and(QEmployee.employee.email.eq(employeeSearch.getEmployeeEmail()));
        }
        if(Utils.isNotNullAndEmpty(employeeSearch.getEmployeeAddress()))
        {
            booleanBuilder.and(QEmployee.employee.address.eq(employeeSearch.getEmployeeAddress()));
        }
        if(Utils.isNotNullAndEmpty(employeeSearch.getEmployeePhoneNumber()))
        {
            booleanBuilder.and(QEmployee.employee.phoneNumber.eq(Long.parseLong(employeeSearch.getEmployeePhoneNumber())));
        }
        if(Utils.isNotNullAndEmpty(employeeSearch.getDesignationName()))
        {
            booleanBuilder.and(QEmployee.employee.designation.name.eq(employeeSearch.getDesignationName()));
        }
        List<EmployeeResponse> employeeResponses=new ArrayList<>();
        Page<Employee> employeePage=employeeRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(employeePage.getTotalElements());
        pagination.setTotalPages(employeePage.getTotalPages());
        for (Employee employee:employeePage
             ) {
            EmployeeResponse employeeResponse=new EmployeeResponse();
            employeeResponse.setDesignationName(employee.getDesignation().getName());
            BeanUtils.copyProperties(employee,employeeResponse);
            employeeResponses.add(employeeResponse);
        }
        return employeeResponses;
    }


}
