package com.defect.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.resquest.dto.DesignationRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.DesignationService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;

@RestController
@CrossOrigin
public class DesignationController {
  @Autowired
  private DesignationService designationService;

  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;

  @PostMapping(value = EndpointURI.DESIGNATION)
  public ResponseEntity<Object> saveDesignation(
      @RequestBody DesignationRequest designationRequest) {
    if (designationService.isDesignationExists(designationRequest.getName())) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getDesignationAlreadyExists(),
          validationFailureResponseCode.getValidationDesignationAlreadyExists()));
    }
    designationService.saveDesignation(designationRequest);
    return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
        validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getSaveDesignationSuccessMessage()));
  }

  @GetMapping(value = EndpointURI.DESIGNATION)
  public ResponseEntity<Object> getAllDesignations() {
    return ResponseEntity
        .ok(new ContentResponse<>(Constants.DESIGNATIONS, designationService.getAllDesignation(),
            RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
            validationFailureResponseCode.getGetAllDesignationSuccessMessage()));
  }



  @GetMapping(value = EndpointURI.DESIGNATION_BY_ID)
  public ResponseEntity<Object> getDesignationById(@PathVariable Long id) {
    if (!designationService.existByDesignation(id)) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getDesignationNotExistsCode(),
          validationFailureResponseCode.getDesignationNotExistsMessage()));
    }
    return ResponseEntity
        .ok(new ContentResponse<>(Constants.DESIGNATION, designationService.getDesignationById(id),
            RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
            validationFailureResponseCode.getGetDesignationByIdSuccessMessage()));
  }

  @PutMapping(value = EndpointURI.DESIGNATION)
  public ResponseEntity<Object> updateDesignation(
      @RequestBody DesignationRequest designationRequest) {
    if (!designationService.existByDesignation(designationRequest.getId())) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getDesignationNotExistsCode(),
          validationFailureResponseCode.getDesignationNotExistsMessage()));
    }
    if (designationService.isUpdatedDesignationNameExist(designationRequest.getId(),
        designationRequest.getName())) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getDesignationAlreadyExists(),
          validationFailureResponseCode.getValidationDesignationAlreadyExists()));
    }
    designationService.saveDesignation(designationRequest);
    return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
        validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getUpdateDesignationSuccessMessage()));
  }

  @DeleteMapping(value = EndpointURI.DESIGNATION_BY_ID)
  public ResponseEntity<Object> deleteDesignation(@PathVariable Long id) {
    if (!designationService.existByDesignation(id)) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getDesignationNotExistsCode(),
          validationFailureResponseCode.getDesignationNotExistsMessage()));
    }
    designationService.deleteDesignation(id);
    return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
        validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getDeleteDesignationSuccessMessage()));
  }
}
