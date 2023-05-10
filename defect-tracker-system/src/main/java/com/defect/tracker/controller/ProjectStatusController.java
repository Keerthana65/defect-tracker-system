package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.resquest.dto.ProjectStatusRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.ProjectStatusService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProjectStatusController {
    @Autowired
    private ProjectStatusService projectStatusService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.PROJECTSTATUS)
    public ResponseEntity<Object> saveProjectStatus(@RequestBody ProjectStatusRequest projectStatusRequest)
    {
        if (projectStatusService.isProjectStatusExists(projectStatusRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusAllreadyExists(),
                    validationFailureResponseCode.getValdiationProjectStatusAllReadyExists()));
        }
        projectStatusService.saveProjectStatus(projectStatusRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveProjectStatusSuccessMessage()));
    }

    @PutMapping(EndpointURI.PROJECTSTATUS)
    public ResponseEntity<Object> updateProjectStatus(@RequestBody ProjectStatusRequest projectStatusRequest)
    {
        if (!projectStatusService.existByProjectStatus(projectStatusRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusNotExists(),
                    validationFailureResponseCode.getValidationProjectStatusnotExists()));
        }
        if(projectStatusService.isUpdatedProjectStatusNameExist(projectStatusRequest.getId(),projectStatusRequest.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusAllreadyExists(),
                    validationFailureResponseCode.getValdiationDefectStatusAllReadyExists()));
        }
        projectStatusService.saveProjectStatus(projectStatusRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getUpdateProjectStatusSuccessMessage()));
    }
    @GetMapping(EndpointURI.PROJECTSTATUS)
    public ResponseEntity<Object> getAllProjectStatus() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTSTATUSES, projectStatusService.getAllProjectStatus(),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllProjectStatusSuccessMessage()));
    }
    @GetMapping(EndpointURI.PROJECTSTATUS_BY_ID)
    public ResponseEntity<Object> getProjectStatusById(@PathVariable Long id) {
        if (!projectStatusService.existByProjectStatus(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusNotExists(),
                    validationFailureResponseCode.getValidationProjectStatusnotExists()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTTSTATUS, projectStatusService.getProjectStatusById(id),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetProjectStatusSuccessMessage()));
    }
    @DeleteMapping(EndpointURI.PROJECTSTATUS_BY_ID)
    public ResponseEntity<Object> deleteProjectStatusByID(@PathVariable Long id)
    {
        if (!projectStatusService.existByProjectStatus(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusNotExists(),
                    validationFailureResponseCode.getValidationProjectStatusnotExists()));
        }
        return ResponseEntity.ok(new BaseResponse(
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteProjectStatusSuccessMessage()));
    }

}