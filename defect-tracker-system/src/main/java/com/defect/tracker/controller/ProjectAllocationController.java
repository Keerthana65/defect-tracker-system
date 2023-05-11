package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.entities.ProjectAllocation;
import com.defect.tracker.resquest.dto.ProjectAllocationRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.EmployeeService;
import com.defect.tracker.service.ProjectAllocationService;
import com.defect.tracker.service.ProjectService;
import com.defect.tracker.service.RoleService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProjectAllocationController {
    @Autowired
    private ProjectAllocationService projectAllocationService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.PROJECTALLOCATION)
    public ResponseEntity<Object> saveProjectAllocation(@RequestBody ProjectAllocationRequest projectAllocationRequest)
    {
        if(!projectService.existsById(projectAllocationRequest.getProjectId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()));
        }
        if(!employeeService.existsById(projectAllocationRequest.getEmployeeId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if(!roleService.existByRole(projectAllocationRequest.getRoleId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getRoleNotExists(),
                    validationFailureResponseCode.getValidationRolenotExists()));
        }
        projectAllocationService.saveProjectAllocation(projectAllocationRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveProjectAllocationSuccessMessage()));
    }
    @PutMapping(EndpointURI.PROJECTALLOCATION)
    public ResponseEntity<Object> updateProjectAllocation(@RequestBody ProjectAllocationRequest projectAllocationRequest)
    {
        if(!projectAllocationService.existsById(projectAllocationRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectallocationNotExists(),
                    validationFailureResponseCode.getProjectallocationNotExistsMessage()));
        }
        if(!projectService.existsById(projectAllocationRequest.getProjectId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()));
        }
        if(!employeeService.existsById(projectAllocationRequest.getEmployeeId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if(!roleService.existByRole(projectAllocationRequest.getRoleId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getRoleNotExists(),
                    validationFailureResponseCode.getValidationRolenotExists()));
        }
        projectAllocationService.saveProjectAllocation(projectAllocationRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getUpdateProjectAllocationSuccessMessage()));

    }
    @GetMapping(EndpointURI.PROJECTALLOCATION)
    public ResponseEntity<Object> getAllProjectAllocation()
    {

        return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTALLOCATIONS,projectAllocationService.getAllProjectAllocation(),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllProjectAllocationSuccessMessage()));
    }
    @GetMapping(EndpointURI.PROJECTALLOCATION_BY_ID)
    public ResponseEntity<Object> getProjectAllocationById(@PathVariable Long id)
    {
        if(!projectAllocationService.existsById(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectallocationNotExists(),
                    validationFailureResponseCode.getProjectallocationNotExistsMessage()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTALLOCATION,projectAllocationService.getProjectAllocationById(id),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetProjectAllocationSuccessMessage()));
    }
    @DeleteMapping(EndpointURI.PROJECTALLOCATION_BY_ID)
    public ResponseEntity<Object> deleteProjectAllocation(@PathVariable Long id)
    {
        if(!projectAllocationService.existsById(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectallocationNotExists(),
                    validationFailureResponseCode.getProjectallocationNotExistsMessage()));
        }
        projectAllocationService.deletePRojectAllocation(id);
        return ResponseEntity.ok(new BaseResponse(
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteProjectAllocationSuccessMessage()));
    }


}
