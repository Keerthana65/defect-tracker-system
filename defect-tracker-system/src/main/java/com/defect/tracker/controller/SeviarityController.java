package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.SeviarityRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.PrioritySearch;
import com.defect.tracker.search.dto.SeviaritySearch;
import com.defect.tracker.service.SeviarityService;
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
public class SeviarityController {

    @Autowired
    private SeviarityService seviarityService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;
    @PostMapping(value = EndpointURI.SEVIARITY)
    public ResponseEntity<Object> saveSeviarity(@RequestBody SeviarityRequest seviarityRequest)
    {
        if(seviarityService.isSeviarityExistsByName(seviarityRequest.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeviarityAlreadyExists()));
        }
        if(seviarityService.isSeviarityExistsByColor(seviarityRequest.getColor()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityAlreadyExists(),
                    validationFailureResponseCode.getValidationSeviarityAlreadyExists()));
        }
        seviarityService.saveSeviarity(seviarityRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSavePrioritySuccessMessage()));
    }

    @GetMapping(value = EndpointURI.SEVIARITY)
    public ResponseEntity<Object> getAllSeviarity()
    {
        return ResponseEntity.ok(new ContentResponse<>(Constants.SEVIARITIES,seviarityService.getAllSeviarity()
                ,RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllSeviaritySuccessMessage() ));
    }

    @GetMapping(value = EndpointURI.SEVIARITY_BY_ID)
    public ResponseEntity<Object> getSeviarityById(@PathVariable Long id)
    {
        if(!seviarityService.existsByPriority(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(), validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.SEVIARITY,seviarityService.getSeviarityById(id),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetSeviaritySuccessMessage()));
    }

    @DeleteMapping(value = EndpointURI.SEVIARITY_BY_ID)
    public ResponseEntity<Object> deleteSeviarity(@PathVariable Long id)
    {
        if(!seviarityService.existsByPriority(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getSeviarityNotExistsCode(),
                    validationFailureResponseCode.getValidationSeviarityNotExists()));
        }
        seviarityService.deleteSeviarity(id);
        return  ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteSeviaritySuccessMessage()));
    }

    @PutMapping(value = EndpointURI.SEVIARITY)
    public ResponseEntity<Object> updateSeviarity(@RequestBody SeviarityRequest seviarityRequest)
    {
     if(!seviarityService.existsByPriority(seviarityRequest.getId()))
     {
         return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                 validationFailureResponseCode.getSeviarityNotExistsCode(),
                 validationFailureResponseCode.getValidationSeviarityNotExists()));
     }
     if(seviarityService.isUpdateSeviarityExistsByName(seviarityRequest.getName(),seviarityRequest.getId()))
     {
         return ResponseEntity.ok((new BaseResponse(RequestStatus.FAILURE.getStatus(),
                 validationFailureResponseCode.getSeviarityAlreadyExists(),
                 validationFailureResponseCode.getValidationSeviarityAlreadyExists())));
     }
     if(seviarityService.isUpdateSeviarityExistsByColor(seviarityRequest.getColor(),seviarityRequest.getId()))
     {
         return ResponseEntity.ok((new BaseResponse(RequestStatus.FAILURE.getStatus(),
                 validationFailureResponseCode.getSeviarityAlreadyExists(),
                 validationFailureResponseCode.getValidationSeviarityAlreadyExists())));
     }
     seviarityService.saveSeviarity(seviarityRequest);
     return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
             validationFailureResponseCode.getUpdateSeviaritySuccessMessage()));
    }
    @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_SEVIARITY)
    public ResponseEntity<Object> multiSearchSeviarity(@RequestParam(name="page") int page,
                                                        @RequestParam(name="size") int size,
                                                        @RequestParam(name="direction") String direction,
                                                        @RequestParam(name="sortField") String sortField, SeviaritySearch seviaritySearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
        return ResponseEntity.ok(new PaginatedContentResponse<>(Constants.SEVIARITIES,seviarityService.multiSearch(pageable,pagination,seviaritySearch),
                RequestStatus.SUCCESS.getStatus(),validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSearchAndPaginationSeviaritySuccessMessage(),pagination));
    }


}
