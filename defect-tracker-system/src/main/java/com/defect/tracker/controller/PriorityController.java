package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.PriorityRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.PrioritySearch;
import com.defect.tracker.service.PriorityService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@CrossOrigin
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(value = EndpointURI.PRIORITY)
    public ResponseEntity<Object> savePriority(@RequestBody PriorityRequest priorityRequest) {
        if (priorityService.isPriorityExistsByName(priorityRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityAlreadyExists(),
                    validationFailureResponseCode.getValidationPriorityAlreadyExists()));
        }
        if (priorityService.isPriorityExistsByColor(priorityRequest.getColor())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityAlreadyExists(),
                    validationFailureResponseCode.getValidationPriorityAlreadyExists()));
        }
        priorityService.savePriority(priorityRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSavePrioritySuccessMessage()));
    }

    @GetMapping(value = EndpointURI.PRIORITY)
    public ResponseEntity<Object> getAllPriority() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.PRIORITIES, priorityService.getAllPriority(),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllPrioritySuccessMessage()));
    }

    @GetMapping(value = EndpointURI.PRIORITY_BY_ID)
    public ResponseEntity<Object> getPriorityById(@PathVariable Long id) {
        if (!priorityService.existsByPriority(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityNotExistsCode(),
                    validationFailureResponseCode.getPriorityNotExistsMessage()));
        }

        return ResponseEntity.ok(new ContentResponse<>(Constants.PRIORITY, priorityService.getPriorityById(id),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllPrioritySuccessMessage()));

    }

    @PutMapping(value = EndpointURI.PRIORITY)
    public ResponseEntity<Object> updatePriority(@RequestBody PriorityRequest priorityRequest) {
        if (!priorityService.existsByPriority(priorityRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityNotExistsCode(),
                    validationFailureResponseCode.getPriorityNotExistsMessage()));
        }
        if (priorityService.isUpdatePriorityNameExists(priorityRequest.getName(), priorityRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityAlreadyExists(),
                    validationFailureResponseCode.getValidationPriorityAlreadyExists()));
        }
        if (priorityService.isUpdatePriorityColorExists(priorityRequest.getColor(), priorityRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityAlreadyExists(),
                    validationFailureResponseCode.getValidationPriorityAlreadyExists()));
        }
        priorityService.savePriority(priorityRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getUpdatePrioritySuccessMessage()));
    }

    @DeleteMapping(value = EndpointURI.PRIORITY_BY_ID)
    public ResponseEntity<Object> deletePriority(@PathVariable Long id) {
        if (!priorityService.existsByPriority(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getPriorityNotExistsCode(),
                    validationFailureResponseCode.getPriorityNotExistsMessage()));
        }
        priorityService.deletePriority(id);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeletePrioritySuccessMessage()));
    }


    @GetMapping(EndpointURI.PRIORITYPAGINATION)
    public ResponseEntity<Object> multiSearchPriority(@RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size,
                                                      @RequestParam(name = "direction") String direction,
                                                      @RequestParam(name = "sortField") String sortField,
                                                      PrioritySearch prioritySearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
       return  ResponseEntity.ok(new PaginatedContentResponse<>(Constants.SEARCHES,
               priorityService.multiSearchpriority(pageable,pagination,prioritySearch),
               RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
               validationFailureResponseCode.getSearchAndAPginationPRioritySuccessMessage(),pagination));
    }
}

//
