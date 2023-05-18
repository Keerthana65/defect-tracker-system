package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.ProjectRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.DefectSearch;
import com.defect.tracker.search.dto.ProjectAllocationSearch;
import com.defect.tracker.search.dto.ProjectSearch;
import com.defect.tracker.service.ProjectService;
import com.defect.tracker.service.ProjectStatusService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectStatusService projectStatusService;
    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.PROJECT)
    public ResponseEntity<Object> saveProject(@RequestBody ProjectRequest projectRequest)
    {
        if(projectService.exisByName(projectRequest.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNameAllReadyExists() ,
                    validationFailureResponseCode.getProjectNameAllreadyExistsMessage()));
        }
        if(!projectStatusService.existByProjectStatus(projectRequest.getProjectStatusId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusNotExists() ,
                    validationFailureResponseCode.getValidationProjectStatusnotExists()));
        }
        projectService.saveProject(projectRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveProjectSuccessMessage()));
    }

    @PutMapping(EndpointURI.PROJECT)
    public ResponseEntity<Object> updateProject(@RequestBody ProjectRequest projectRequest)
    {
        if(!projectService.existsById(projectRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()));
        }
        if(projectService.existsByProjectNameAndIdNot(projectRequest.getName(),projectRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNameAllReadyExists(),
                    validationFailureResponseCode.getProjectNameAllreadyExistsMessage()));
        }
        if(!projectStatusService.existByProjectStatus(projectRequest.getProjectStatusId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusNotExists() ,
                    validationFailureResponseCode.getValidationProjectStatusnotExists()));
        }

        projectService.saveProject(projectRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getUpdateProjectSuccessMessage()));
    }
    @GetMapping(EndpointURI.PROJECT)
    public ResponseEntity<Object> getALlProject()
    {
        return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTS,projectService.getAllProject(),
                RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode() ,
                validationFailureResponseCode.getGetAllProjectSuccessMessage()));
    }
    @GetMapping(EndpointURI.PROJECT_BY_ID)
    public ResponseEntity<Object> getProjectById(@PathVariable Long id)
    {
        if(!projectService.existsById(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECT,projectService.getProjectById(id),
                RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode() ,
                validationFailureResponseCode.getGetProjectSuccessMessage()));
    }
    @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_PROJECT)
    public ResponseEntity<Object> multiSearchDefectType(@RequestParam(name="page") int page,
                                                        @RequestParam(name="size") int size,
                                                        @RequestParam(name="direction") String direction,
                                                        @RequestParam(name="sortField") String sortField,
                                                        ProjectSearch projectSearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
        return ResponseEntity.ok(new PaginatedContentResponse<>(Constants.PROJECTS,projectService.multiSearchProject(pageable,pagination,projectSearch),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),validationFailureResponseCode.getSearchAndPaginationProjectSuccessMessage(),pagination));
    }
    @DeleteMapping(EndpointURI.PROJECT_BY_ID)
    public ResponseEntity<Object> deleteProject(@PathVariable Long id)
    {
        if(!projectService.existsById(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectNotExists(),
                    validationFailureResponseCode.getProjectNotExistsMessage()));
        }
        projectService.deleteProject(id);
        return ResponseEntity.ok(new BaseResponse(
                RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode() ,
                validationFailureResponseCode.getDeleteProjectSuccessMessage()));
    }

}
