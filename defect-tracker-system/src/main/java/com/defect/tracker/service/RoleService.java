package com.defect.tracker.service;

import com.defect.tracker.response.dto.ReleaseResponse;
import com.defect.tracker.response.dto.RoleResponse;
import com.defect.tracker.resquest.dto.ReleaseRequest;
import com.defect.tracker.resquest.dto.RoleRequest;

import java.util.List;

public interface RoleService{
    public void saveRole(RoleRequest roleRequest);

        public List<RoleResponse> getAllRoles();

        public boolean isRoleExists(String name);

        public RoleResponse getRoleById(Long id);

        public boolean existByRole(Long id);

        public boolean isUpdatedRoleNameExist(Long id, String name);

        public void deleteRole(Long id);
}