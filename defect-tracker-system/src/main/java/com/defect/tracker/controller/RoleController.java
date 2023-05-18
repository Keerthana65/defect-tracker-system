package com.defect.tracker.controller;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.resquest.dto.RoleRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.search.dto.ReleaseSearch;
import com.defect.tracker.search.dto.RoleSearch;
import com.defect.tracker.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ValidationFailureResponseCode validationFailureResponseCode;

    @PostMapping(EndpointURI.ROLE)
    public ResponseEntity<Object> saveRole(@RequestBody RoleRequest roleRequest) {
        if (roleService.isRoleExists(roleRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getRoleAllreadyExists(),
                    validationFailureResponseCode.getValdationRoleAllReadyExists()));
        }

        roleService.saveRole(roleRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getSaveRoleSuccessMessage()));
    }

    @PutMapping(EndpointURI.ROLE)
    public ResponseEntity<Object> updateRole(@RequestBody RoleRequest roleRequest) {
        if (!roleService.existByRole(roleRequest.getId())) {
            return ResponseEntity.ok(new BaseResponse((RequestStatus.FAILURE.getStatus()),
                    validationFailureResponseCode.getRoleNotExists(),
                    validationFailureResponseCode.getValidationRolenotExists()));

        }
        if (roleService.isRoleExists(roleRequest.getName())) {
            return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
                    validationFailureResponseCode.getRoleAllreadyExists(),
                    validationFailureResponseCode.getValdationRoleAllReadyExists()));
        }
        roleService.saveRole(roleRequest);
        return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
                validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getUpdateRoleSuccessMessage()));
    }

    @GetMapping(EndpointURI.ROLE)
    public ResponseEntity<Object> getAllRole() {
        return ResponseEntity.ok(new ContentResponse<>(Constants.ROLES, roleService.getAllRoles(),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetAllRoleSuccessMessage()));
    }

    @GetMapping(EndpointURI.ROLE_BY_ID)
    public ResponseEntity<Object> getRoleById(@PathVariable Long id) {
        if (!roleService.existByRole(id)) {
            return ResponseEntity.ok(new BaseResponse((RequestStatus.FAILURE.getStatus()),
                    validationFailureResponseCode.getRoleNotExists(),
                    validationFailureResponseCode.getValidationRolenotExists()));

        }
        return ResponseEntity.ok(new ContentResponse<>(Constants.ROLE, roleService.getRoleById(id),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getGetRoleSuccessMessage()));

    }

    @GetMapping(EndpointURI.SEARCH_AND_PAGINATION_ROLE)
    public ResponseEntity<Object> multiSearchDefectType(@RequestParam(name="page") int page,
                                                        @RequestParam(name="size") int size,
                                                        @RequestParam(name="direction") String direction,
                                                        @RequestParam(name="sortField") String sortField,
                                                        RoleSearch roleSearch)
    {
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.valueOf(direction),sortField);
        PaginatedContentResponse.Pagination pagination=new PaginatedContentResponse.Pagination(page,size,0,0l);
        return ResponseEntity.ok(new PaginatedContentResponse<>(Constants.ROLES,roleService.multiSearchRole(pageable,pagination,roleSearch),
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),validationFailureResponseCode.getSearchAndPaginationRoleSuccessMessage(),pagination));
    }

    @DeleteMapping(EndpointURI.ROLE_BY_ID)
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        if (!roleService.existByRole(id)) {
            return ResponseEntity.ok(new BaseResponse((RequestStatus.FAILURE.getStatus()),
                    validationFailureResponseCode.getRoleNotExists(),
                    validationFailureResponseCode.getValidationRolenotExists()));

        }
        roleService.deleteRole(id);
        return ResponseEntity.ok(new BaseResponse(
                RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
                validationFailureResponseCode.getDeleteRoleSuccessMessage()));
    }

}
