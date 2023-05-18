package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.SeverityRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.SeveritySearch;
import com.defect.tracker.service.SeverityService;
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
public class SeverityController {

    @Autowired
    private SeverityService seviarityService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;
    @PostMapping(value = EndpointURI.SEVERITY)
    public ResponseEntity<Object> saveSeverity(@RequestBody SeverityRequest seviarityRequest)
    {
        if(seviarityService.isSeverityExistsByName(seviarityRequest.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeverityAlreadyExists()));
        }
        if(seviarityService.isSeverityExistsByColor(seviarityRequest.getColor()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeverityAlreadyExists()));
        }
        seviarityService.saveSeverity(seviarityRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveSeviaritySuccessMessage()));
    }

    @GetMapping(value = EndpointURI.SEVERITY)
    public ResponseEntity<Object> getAllSeverity()
    {
        return ResponseEntity.ok(new ContentResponse<>(Constants.SEVERITIES,seviarityService.getAllSeverity()
                ,RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllSeviaritySuccessMessage() ));
    }

    @GetMapping(value = EndpointURI.SEVERITY_BY_ID)
    public ResponseEntity<Object> getSeverityById(@PathVariable Long id)
    {
        if(!seviarityService.existsByPriority(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(), validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.SEVERITY,seviarityService.getSeverityById(id),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetSeviaritySuccessMessage()));
    }

    @DeleteMapping(value = EndpointURI.SEVERITY_BY_ID)
    public ResponseEntity<Object> deleteSeverity(@PathVariable Long id)
    {
        if(!seviarityService.existsByPriority(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        seviarityService.deleteSeverity(id);
        return  ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteSeviaritySuccessMessage()));
    }

    @PutMapping(value = EndpointURI.SEVERITY)
    public ResponseEntity<Object> updateSeverity(@RequestBody SeverityRequest seviarityRequest)
    {
     if(!seviarityService.existsByPriority(seviarityRequest.getId()))
     {
         return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                 validationFailureResponseCode.getSeviarityNotExistsCode(),
                 validationFailureResponseCode.getValidationSeviarityNotExists()));
     }
     if(seviarityService.isUpdateSeverityExistsByName(seviarityRequest.getName(),seviarityRequest.getId()))
     {
         return ResponseEntity.ok((new BaseResponse(RequestStatus.FAILURE.getStatus(),
                 validationFailureResponseCode.getSeviarityAlreadyExists(),
                 validationFailureResponseCode.getValidationSeverityAlreadyExists())));
     }
     if(seviarityService.isUpdateSeverityExistsByColor(seviarityRequest.getColor(),seviarityRequest.getId()))
     {
         return ResponseEntity.ok((new BaseResponse(RequestStatus.FAILURE.getStatus(),
                 validationFailureResponseCode.getSeviarityAlreadyExists(),
                 validationFailureResponseCode.getValidationSeverityAlreadyExists())));
     }
     seviarityService.saveSeverity(seviarityRequest);
     return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
             validationFailureResponseCode.getUpdateSeviaritySuccessMessage()));
    }
    @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_SEVIARITY)
    public ResponseEntity<Object> multiSearchSeviarity(@RequestParam(name="page") int page,
                                                        @RequestParam(name="size") int size,
                                                        @RequestParam(name="direction") String direction,
                                                        @RequestParam(name="sortField") String sortField, SeveritySearch seviaritySearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
        return ResponseEntity.ok(new PaginatedContentResponse<>(Constants.SEVERITIES,seviarityService.multiSearch(pageable,pagination,seviaritySearch),
                RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSearchAndPaginationSeviaritySuccessMessage(),pagination));
    }


}
