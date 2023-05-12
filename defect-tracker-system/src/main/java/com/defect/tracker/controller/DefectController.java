package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.resquest.dto.DefectRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.*;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class DefectController {
    @Autowired
    private DefectService defectService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PriorityService priorityService;
    @Autowired
    private SeviarityService seviarityService;
    @Autowired
    private DefectStatusService defectStatusService;
    @Autowired
    private DefectTypeService defectTypeService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReleaseService releaseService;
    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.DEFECT)
    public ResponseEntity<Object> saveDefect(@RequestBody DefectRequest defectRequest)
    {
        if(defectService.existsByName(defectRequest.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getNameAllReadyExists(),validationFailureResponseCode.getNameAllRedyExistsMessage()));
        }
        if(!projectService.existsById(defectRequest.getProjectId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()
                    ));

        }
        if(!priorityService.existsByPriority(defectRequest.getPriorityId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityNotExistsCode(),
                    validationFailureResponseCode.getPriorityNotExistsMessage()));
        }
        if(!seviarityService.existsByPriority(defectRequest.getSeviarityId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        if(!defectStatusService.existByDefectStatus(defectRequest.getDefectStatusId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusNotExists(),
                    validationFailureResponseCode.getValidationDefcetStatusnotExists()));
        }
        if(!defectTypeService.existsByDefectType(defectRequest.getDefectTypeId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeNotExists(),
                    validationFailureResponseCode.getValidationDefcetTypenotExists()));
        }
        if(!employeeService.existsById(defectRequest.getReportId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if(!employeeService.existsById(defectRequest.getAssigToId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        defectService.saveDefect(defectRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveDefectSuccessMessage()));
    }
    @PutMapping(EndpointURI.DEFECT)
    public ResponseEntity<Object> updateDefect(@RequestBody DefectRequest defectRequest)
    {
        if(!defectService.existById(defectRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectNotExists(),validationFailureResponseCode.getDefectNotExitsMessage()));
        }
        if(defectService.existsByNameIgnoreCaseAndIdNot(defectRequest.getName(),defectRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getNameAllReadyExists(),
                    validationFailureResponseCode.getNameAllRedyExistsMessage()));
        }
        if(!projectService.existsById(defectRequest.getProjectId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()
            ));

        }
        if(!priorityService.existsByPriority(defectRequest.getPriorityId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityNotExistsCode(),
                    validationFailureResponseCode.getPriorityNotExistsMessage()));
        }
        if(!seviarityService.existsByPriority(defectRequest.getSeviarityId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        if(!defectStatusService.existByDefectStatus(defectRequest.getDefectStatusId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusNotExists(),
                    validationFailureResponseCode.getValidationDefcetStatusnotExists()));
        }
        if(!defectTypeService.existsByDefectType(defectRequest.getDefectTypeId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeNotExists(),
                    validationFailureResponseCode.getValidationDefcetTypenotExists()));
        }
        if(!employeeService.existsById(defectRequest.getReportId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if(!employeeService.existsById(defectRequest.getAssigToId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        defectService.saveDefect(defectRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveDefectSuccessMessage()));
    }
    @GetMapping(EndpointURI.DEFECT)
    public ResponseEntity<Object> getAllDefect()
    {
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECTS,defectService.getllDefect(),RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllDefectSuccessMessage()));
    }
    @GetMapping(EndpointURI.DEFECT_BY_ID)
    public ResponseEntity<Object> getDefectById(@PathVariable Long id)
    {
        if(!defectService.existById(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectNotExists(),validationFailureResponseCode.getDefectNotExitsMessage()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECT,defectService.getDefectById(id),RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetDefctSuccessMessage()));
    }
    @DeleteMapping(EndpointURI.DEFECT_BY_ID)
    public ResponseEntity<Object> deleteDefectById(@PathVariable Long id)
    {
        if(!defectService.existById(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectNotExists(),validationFailureResponseCode.getDefectNotExitsMessage()));
        }
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteDefctSuccessMessage()));
    }

}