package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.resquest.dto.DefecetStatusRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.DefectStatusService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class DefectStatusController {
    @Autowired
    private DefectStatusService defectStatusService;
    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.DEFECTSTATUS)
    public ResponseEntity<Object> saveDefectStatus(@RequestBody DefecetStatusRequest defecetStatusRequest) {
        if (defectStatusService.isDefectStatusExists(defecetStatusRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusAllreadyExists(),
                    validationFailureResponseCode.getValdiationDefectStatusAllReadyExists()));
        }
        defectStatusService.saveDefectStatus(defecetStatusRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveDefectStatusSuccessMessage()));
    }

    @PutMapping(EndpointURI.DEFECTSTATUS)
    public ResponseEntity<Object> updateDefectStatus(@RequestBody DefecetStatusRequest defecetStatusRequest) {
        if (!defectStatusService.existByDefectStatus(defecetStatusRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusNotExists(),
                    validationFailureResponseCode.getValidationDefcetStatusnotExists()));
        }
        if (defectStatusService.isUpdatedDefectStatusNameExist(defecetStatusRequest.getId(), defecetStatusRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusAllreadyExists(),
                    validationFailureResponseCode.getValdiationDefectStatusAllReadyExists()));
        }
        defectStatusService.saveDefectStatus(defecetStatusRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveDefectStatusSuccessMessage()));

    }

    @GetMapping(EndpointURI.DEFECTSTATUS)
    public ResponseEntity<Object> getAllDefectStatus() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECTSTATUSES, defectStatusService.getAllDefectStatus(),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllDefectStatusSuccessMessage()));
    }

    @GetMapping(EndpointURI.DEFECTSTATUS_BY_ID)
    public ResponseEntity<Object> getDefectStatus(@PathVariable Long id) {
        if (!defectStatusService.existByDefectStatus(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusNotExists(),
                    validationFailureResponseCode.getValidationDefcetStatusnotExists()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.DEFECTSTATUS, defectStatusService.getDefectStatusById(id),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetDefectStatusSuccessMessage()));

    }

    @DeleteMapping(EndpointURI.DEFECTSTATUS_BY_ID)
    public ResponseEntity<Object> deleteDefectStatus(@PathVariable Long id) {
        if (!defectStatusService.existByDefectStatus(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getDefectStatusNotExists(),
                    validationFailureResponseCode.getValidationDefcetStatusnotExists()));
        }
        defectStatusService.deleteDefectStatus(id);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteDefectStatusSuccessMessage()));

    }
}
