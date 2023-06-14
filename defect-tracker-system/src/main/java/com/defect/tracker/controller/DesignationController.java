package com.defect.tracker.controller;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.search.dto.DefectTypeSearch;
import com.defect.tracker.search.dto.DesiginationSearch;
import com.defect.tracker.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
@PreAuthorize(value = User.ADMINN)
public class DesignationController {
  @Autowired
  private DesignationService designationService;

  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @PreAuthorize(value = User.USERS)
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

  @PreAuthorize(value = User.USERS)
  @GetMapping(value = EndpointURI.DESIGNATION)
  public ResponseEntity<Object> getAllDesignations() {
    return ResponseEntity
        .ok(new ContentResponse<>(Constants.DESIGNATIONS, designationService.getAllDesignation(),
            RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
            validationFailureResponseCode.getGetAllDesignationSuccessMessage()));
  }


  @PreAuthorize(value = User.USERS)
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
  @PreAuthorize(value = User.USERS)
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
  @PreAuthorize(value = User.USERS)
  @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_DESIGNATION)
  public ResponseEntity<Object> multiSearchDefectType(@RequestParam(name="page") int page,
                                                      @RequestParam(name="size") int size,
                                                      @RequestParam(name="direction") String direction,
                                                      @RequestParam(name="sortField") String sortField,
                                                      DesiginationSearch desiginationSearch)
  {
    Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
    PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
    return ResponseEntity.ok(new PaginatedContentResponse<>(Constants.DESIGNATIONS,designationService.multiSearchDesignationSearch(pageable,pagination,desiginationSearch),
            RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),validationFailureResponseCode.getSearchAndPaginationDesignationSuccessMessage(),pagination));
  }
  @PreAuthorize(value = User.USERS)
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
