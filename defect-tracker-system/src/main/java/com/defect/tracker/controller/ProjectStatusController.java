package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.ProjectStatusRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.PrioritySearch;
import com.defect.tracker.search.dto.ProjectStatusSearch;
import com.defect.tracker.service.ProjectStatusService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.User;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize(value = User.ADMINN)
public class ProjectStatusController {
    @Autowired
    private ProjectStatusService projectStatusService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PreAuthorize(value = User.USERS)
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
    @PreAuthorize(value = User.USERS)
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
    @PreAuthorize(value = User.USERS)
    @GetMapping(EndpointURI.PROJECTSTATUS)
    public ResponseEntity<Object> getAllProjectStatus() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTSTATUSES, projectStatusService.getAllProjectStatus(),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllProjectStatusSuccessMessage()));
    }
    @PreAuthorize(value = User.USERS)
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
    @PreAuthorize(value = User.USERS)
    @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_PROJECTSTATUS)
    public ResponseEntity<Object> multiSearchPriority(@RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size,
                                                      @RequestParam(name = "direction") String direction,
                                                      @RequestParam(name = "sortField") String sortField,
                                                      ProjectStatusSearch projectStatusSearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
        return  ResponseEntity.ok(new PaginatedContentResponse<>(Constants.PROJECTSTATUSES,
                projectStatusService.multiSearchProjectStatusSearch(pageable,pagination,projectStatusSearch),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSearchAndAPginationProjectStatusSuccessMessage(),pagination));
    }
    @PreAuthorize(value = User.USERS)
    @DeleteMapping(EndpointURI.PROJECTSTATUS_BY_ID)
    public ResponseEntity<Object> deleteProjectStatusByID(@PathVariable Long id)
    {
        if (!projectStatusService.existByProjectStatus(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusNotExists(),
                    validationFailureResponseCode.getValidationProjectStatusnotExists()));
        }
        projectStatusService.deleteProjectStatus(id);
        return ResponseEntity.ok(new BaseResponse(
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteProjectStatusSuccessMessage()));
    }

}
