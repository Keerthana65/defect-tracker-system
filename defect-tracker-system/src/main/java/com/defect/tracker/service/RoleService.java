package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.ReleaseResponse;
import com.defect.tracker.response.dto.RoleResponse;
import com.defect.tracker.resquest.dto.ReleaseRequest;
import com.defect.tracker.resquest.dto.RoleRequest;
import com.defect.tracker.search.dto.ReleaseSearch;
import com.defect.tracker.search.dto.RoleSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService{
    public void saveRole(RoleRequest roleRequest);

        public List<RoleResponse> getAllRoles();

        public boolean isRoleExists(String name);

        public RoleResponse getRoleById(Long id);

        public boolean existByRole(Long id);

        public boolean isUpdatedRoleNameExist(Long id, String name);

        public void deleteRole(Long id);
        public List<RoleResponse> multiSearchRole(Pageable pageable, PaginatedContentResponse.Pagination pagination, RoleSearch roleseSearch);
}