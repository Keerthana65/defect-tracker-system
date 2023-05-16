package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.entities.Employee;
import com.defect.tracker.resquest.dto.EmployeeRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.DesignationService;
import com.defect.tracker.service.EmployeeService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DesignationService designationService;
    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.EMPLOYEE)
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeRequest employeeRequest) {
        if (employeeService.existsByEmployeeEmail(employeeRequest.getEmail())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeEmailAllReadyExists(),
                    validationFailureResponseCode.getValidationEmployeeEmailAllReadyExists()));
        }
        if (employeeService.existsByPhoneNumber(employeeRequest.getPhoneNumber())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeePhoneNumberAllReadyExists(),
                    validationFailureResponseCode.getValidationEmployeePhoneNumberAllReadyExists()));
        }
        if (!designationService.existByDesignation(employeeRequest.getDesignationId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDesignationNotExistsCode(),
                    validationFailureResponseCode.getDesignationNotExistsMessage()));
        }
        employeeService.saveEmployee(employeeRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveEmployeeSuccessMessage()));
    }

    @PutMapping(EndpointURI.EMPLOYEE)
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        if (!employeeService.existsById(employeeRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if (employeeService.isUpdateEmployeeEmailALreadyExists(employeeRequest.getEmail(), employeeRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeEmailAllReadyExists(),
                    validationFailureResponseCode.getValidationEmployeeEmailAllReadyExists()));
        }
        if (employeeService.isUpdateEmployeePhonenumbeAlreadyExists(employeeRequest.getPhoneNumber(), employeeRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeePhoneNumberAllReadyExists(),
                    validationFailureResponseCode.getValidationEmployeePhoneNumberAllReadyExists()));
        }
        if (!designationService.existByDesignation(employeeRequest.getDesignationId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDesignationNotExistsCode(),
                    validationFailureResponseCode.getDesignationNotExistsMessage()));
        }

        employeeService.saveEmployee(employeeRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveEmployeeSuccessMessage()));
    }

    @GetMapping(EndpointURI.EMPLOYEE)
    public ResponseEntity<Object> getAllEmployee() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.EMPLOYEES, employeeService.getAllEmployee(), RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(), validationFailureResponseCode.getGetAllEmployeeSUccessMessage()));
    }

    @GetMapping(EndpointURI.EMPLOYEE_BY_ID)
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        if (!employeeService.existsById(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }

        return ResponseEntity.ok(new ContentResponse<>(Constants.EMPLOYEE, employeeService.getEmployeeById(id),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetEmployeeByIdSuccessMessage()));
    }
    @DeleteMapping(EndpointURI.EMPLOYEE_BY_ID)
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id)
    {
        if (!employeeService.existsById(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        employeeService.deleteEmployee(id);

        return ResponseEntity.ok(new BaseResponse(
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteEMployeeSuccessMessage()));
    }
//    @RequestMapping(value = "/paginationSorting/{pageNumber}/{pageSize}/{sortPropertity}",method =RequestMethod.GET)
//    public Page<Employee> employeePagination(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String sortproperty)
//    {
//        return employeeService.getEmployeePagination(pageNumber,pageSize,sortproperty);
//    }
    @GetMapping(EndpointURI.paginationAndSorting)
    public ResponseEntity<Object> employeePagination(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String sortproperty)
    {
        return ResponseEntity.ok(new ContentResponse<>(Constants.SEARCHES,employeeService.getEmployeePagination(pageNumber,pageSize,sortproperty),RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),validationFailureResponseCode.getGetEmployeePainationSuccessMessage()));
    }
}
