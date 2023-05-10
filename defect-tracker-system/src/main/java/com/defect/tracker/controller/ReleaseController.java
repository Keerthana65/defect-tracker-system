package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.resquest.dto.ReleaseRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.ReleaseService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReleaseController {
    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.RELEASE)
    public ResponseEntity<Object> saveRelease(@RequestBody ReleaseRequest releaseRequest) {
        if (releaseService.isReleaseExists(releaseRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getReleaseAllreadyExists(),
                    validationFailureResponseCode.getValidationReleasenotExists()));
        }
        releaseService.saveRelease(releaseRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveReleaseSuccessMessage()));
    }

    @PutMapping(EndpointURI.RELEASE)
    public ResponseEntity<Object> updateRelease(@RequestBody ReleaseRequest releaseRequest) {
        if (!releaseService.existByRelease(releaseRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getReleaseNotExists(),
                    validationFailureResponseCode.getValidationReleasenotExists()));
        }
        if (releaseService.isUpdatedReleaseNameExist(releaseRequest.getId(), releaseRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getProjectStatusAllreadyExists(),
                    validationFailureResponseCode.getValidationRolenotExists()));
        }
        releaseService.saveRelease(releaseRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveReleaseSuccessMessage()));
    }

    @GetMapping(EndpointURI.RELEASE)
    public ResponseEntity<Object> getAllRelease() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.RELEASES, releaseService.getAllRelease(),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllReleaseSuccessMessage()));
    }

    @GetMapping(EndpointURI.RELEASE_BY_ID)
    public ResponseEntity<Object> getReleaseById(@PathVariable Long id) {
        if (!releaseService.existByRelease(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getReleaseNotExists(),
                    validationFailureResponseCode.getValidationReleasenotExists()));
        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.RELEASE, releaseService.getReleaseById(id),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetReleaseSuccessMessage()));

    }

    @DeleteMapping(EndpointURI.RELEASE_BY_ID)
    public ResponseEntity<Object> deleteReleseById(@PathVariable Long id) {
        if (!releaseService.existByRelease(id)) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getReleaseNotExists(),
                    validationFailureResponseCode.getValidationReleasenotExists()));
        }
        releaseService.deleteRelease(id);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteReleaseSuccessMessage()));
    }
}
