package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.DefectRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.DefectSearch;
import com.defect.tracker.service.*;
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
public class DefectController {
    @Autowired
    private DefectService defectService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PriorityService priorityService;
    @Autowired
    private SeverityService seviarityService;
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

    @PreAuthorize(value = User.USERS)
    @PostMapping(EndpointURI.DEFECT)
    public ResponseEntity<Object> saveDefect(@RequestBody DefectRequest defectRequest) {
        if (defectService.existsByName(defectRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getNameAllReadyExists(), validationFailureResponseCode.getNameAllRedyExistsMessage()));
        }
        if (!projectService.existsById(defectRequest.getProjectId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()
            ));

        }
        if (!priorityService.existsByPriority(defectRequest.getPriorityId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityNotExistsCode(),
                    validationFailureResponseCode.getPriorityNotExistsMessage()));
        }
        if (!seviarityService.existsByPriority(defectRequest.getSeviarityId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        if (!defectStatusService.existByDefectStatus(defectRequest.getDefectStatusId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusNotExists(),
                    validationFailureResponseCode.getValidationDefcetStatusnotExists()));
        }
        if (!defectTypeService.existsByDefectType(defectRequest.getDefectTypeId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeNotExists(),
                    validationFailureResponseCode.getValidationDefcetTypenotExists()));
        }
        if (!employeeService.existsById(defectRequest.getReportId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if (!employeeService.existsById(defectRequest.getAssigToId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if(!releaseService.existByRelease(defectRequest.getReleaseId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getReleaseNotExists(),
                    validationFailureResponseCode.getValidationReleasenotExists()));
        }
        defectService.saveDefect(defectRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveDefectSuccessMessage()));
    }

    @PreAuthorize(value = User.USERS)
    @PutMapping(EndpointURI.DEFECT)
    public ResponseEntity<Object> updateDefect(@RequestBody DefectRequest defectRequest) {
        if (!defectService.existById(defectRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectNotExists(), validationFailureResponseCode.getDefectNotExitsMessage()));
        }
        if (defectService.existsByNameIgnoreCaseAndIdNot(defectRequest.getName(), defectRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getNameAllReadyExists(),
                    validationFailureResponseCode.getNameAllRedyExistsMessage()));
        }
        if (!projectService.existsById(defectRequest.getProjectId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()
            ));

        }
        if (!priorityService.existsByPriority(defectRequest.getPriorityId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityNotExistsCode(),
                    validationFailureResponseCode.getPriorityNotExistsMessage()));
        }
        if (!seviarityService.existsByPriority(defectRequest.getSeviarityId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        if (!defectStatusService.existByDefectStatus(defectRequest.getDefectStatusId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusNotExists(),
                    validationFailureResponseCode.getValidationDefcetStatusnotExists()));
        }
        if (!defectTypeService.existsByDefectType(defectRequest.getDefectTypeId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeNotExists(),
                    validationFailureResponseCode.getValidationDefcetTypenotExists()));
        }
        if (!employeeService.existsById(defectRequest.getReportId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if (!employeeService.existsById(defectRequest.getAssigToId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getEmployeeNotExists(),
                    validationFailureResponseCode.getValidationEmployeeNotExists()));
        }
        if(!releaseService.existByRelease(defectRequest.getReleaseId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getReleaseNotExists(),
                    validationFailureResponseCode.getValidationReleasenotExists()));
        }
        defectService.saveDefect(defectRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getUpdateDefectSucessMessage()));
    }

    @PreAuthorize(value = User.USERS)
    @GetMapping(EndpointURI.DEFECT)
    public ResponseEntity<Object> getAllDefect() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECTS, defectService.getllDefect(), RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllDefectSuccessMessage()));
    }

    @PreAuthorize(value = User.USERS)
    @GetMapping(EndpointURI.DEFECT_BY_ID)
    public ResponseEntity<Object> getDefectById(@PathVariable Long id) {
        if (!defectService.existById(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectNotExists(), validationFailureResponseCode.getDefectNotExitsMessage()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECT, defectService.getDefectById(id), RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetDefctSuccessMessage()));
    }

    @PreAuthorize(value = User.USERS)
    @DeleteMapping(EndpointURI.DEFECT_BY_ID)
    public ResponseEntity<Object> deleteDefectById(@PathVariable Long id) {
        if (!defectService.existById(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectNotExists(), validationFailureResponseCode.getDefectNotExitsMessage()));
        }
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteDefctSuccessMessage()));
    }
    @PreAuthorize(value = User.USERS)
    @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_DEFECT)
    public ResponseEntity<Object> multiSearchDefectType(@RequestParam(name="page") int page,
                                                        @RequestParam(name="size") int size,
                                                        @RequestParam(name="direction") String direction,
                                                        @RequestParam(name="sortField") String sortField,
                                                        DefectSearch defectSearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
        return ResponseEntity.ok(new PaginatedContentResponse<>(Constants.DEFECTS,defectService.multiSearchDefect(pageable,pagination,defectSearch),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),validationFailureResponseCode.getSearchAndPaginationDefectnSuccessMessage(),pagination));
    }


}
