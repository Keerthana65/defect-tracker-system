package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.DefectTypeRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.DefectTypeSearch;
import com.defect.tracker.service.DefectTypeService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;

@RestController
@CrossOrigin
public class DefectTypeController {
    @Autowired
    private DefectTypeService defectTypeService;
    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.DEFECTTYPE)
    public ResponseEntity<Object> createDefectType(@RequestBody DefectTypeRequest defectTypeRequest)
    {
        if(defectTypeService.isDefectTypeExits(defectTypeRequest.getName()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeAllreadyExists(),
                    validationFailureResponseCode.getValidationSeverityAlreadyExists()));
        }
        defectTypeService.saveDefectType(defectTypeRequest);
        return  ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveDefectTypeSuccessMessage()));
    }

    @PutMapping(value = EndpointURI.DEFECTTYPE)
    public ResponseEntity<Object> updateDefectType(@RequestBody DefectTypeRequest defectTypeRequest)
    {
        if (!defectTypeService.existsByDefectType(defectTypeRequest.getId())
        ) {

            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeNotExists(),
                    validationFailureResponseCode.getValidationDefcetTypenotExists()));
        }
        if(defectTypeService.updateDefectTypeNameExits(defectTypeRequest.getName(),defectTypeRequest.getId()))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeAllreadyExists(),
                    validationFailureResponseCode.getValidationDesignationAlreadyExists()));
        }
        defectTypeService.saveDefectType(defectTypeRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getUpdateDefectTypeSuccessMessage()));
    }

    @GetMapping(EndpointURI.DEFECTTYPE)
    public ResponseEntity<Object> getAllDefectType()
    {
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECTTYPES,defectTypeService.getAllDefectType(),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllDefectTypeSuccessMessage()));
    }

    @GetMapping(EndpointURI.DEFECTTYPE_BY_ID)
    public ResponseEntity<Object> getDefectTypeById(@PathVariable Long id)
    {
        if(!defectTypeService.existsByDefectType(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeNotExists(),
                    validationFailureResponseCode.getValidationDefcetTypenotExists()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECTTYPE,defectTypeService.getDefectTypeById(id),
                RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetDefectTypeSuccessMessage()));
    }
    @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_DEFECTTYPE)
    public ResponseEntity<Object> multiSearchDefectType(@RequestParam(name="page") int page,
                                                        @RequestParam(name="size") int size,
                                                        @RequestParam(name="direction") String direction,
                                                        @RequestParam(name="sortField") String sortField,
                                                        DefectTypeSearch defectTypeSearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
        return ResponseEntity.ok(new PaginatedContentResponse<>(Constants.DEFECTTYPES,defectTypeService.multiSearchDefectType(pageable,pagination,defectTypeSearch),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),validationFailureResponseCode.getSearchAndPaginationDefectTypeSuccessMessage(),pagination));
    }



    @DeleteMapping(EndpointURI.DEFECTTYPE_BY_ID)
    public ResponseEntity<Object> deleteDefectTypeById(@PathVariable Long id)
    {
        if(!defectTypeService.existsByDefectType(id))
        {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectTypeNotExists(),
                    validationFailureResponseCode.getValidationDefcetTypenotExists()));
        }
        defectTypeService.deleteDefctType(id);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteDefectTypeSuccessMessage()));

    }




}
