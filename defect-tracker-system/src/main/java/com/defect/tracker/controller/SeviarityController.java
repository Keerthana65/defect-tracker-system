package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.resquest.dto.SeviarityRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.SeviarityService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
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


}
