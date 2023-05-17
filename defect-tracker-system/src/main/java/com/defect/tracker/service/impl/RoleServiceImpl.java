package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Role;
import com.defect.tracker.entities.QRole;
import com.defect.tracker.repositories.RoleRepository;
import com.defect.tracker.response.dto.ReleaseResponse;
import com.defect.tracker.response.dto.RoleResponse;
import com.defect.tracker.resquest.dto.RoleRequest;
import com.defect.tracker.search.dto.RoleSearch;
import com.defect.tracker.service.RoleService;
import com.defect.tracker.utils.Utils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveRole(RoleRequest roleRequest) {
        Role role = new Role();
        BeanUtils.copyProperties(roleRequest, role);
        roleRepository.save(role);

    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();
        for (Role role : roles
        ) {
            RoleResponse roleResponse = new RoleResponse();
            BeanUtils.copyProperties(role, roleResponse);
            roleResponses.add(roleResponse);
        }
        return roleResponses;
    }

    @Override
    public boolean isRoleExists(String name) {
        return roleRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id).get();
        RoleResponse roleResponse = new RoleResponse();
        BeanUtils.copyProperties(role, roleResponse);
        return roleResponse;
    }

    @Override
    public boolean existByRole(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public boolean isUpdatedRoleNameExist(Long id, String name) {
        return roleRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleResponse> multiSearchRole(Pageable pageable, PaginatedContentResponse.Pagination pagination, RoleSearch roleseSearch)
    {
            BooleanBuilder booleanBuilder=new BooleanBuilder();
            if(Utils.isNotNullAndEmpty(roleseSearch.getRoleName()))
            {
                booleanBuilder.and(QRole.role.name.eq(roleseSearch.getRoleName()));
            }
            List<RoleResponse> roleResponseList=new ArrayList<>();
            Page<Role> rolePage=roleRepository.findAll(booleanBuilder,pageable);
            pagination.setTotalRecords(rolePage.getTotalElements());
            pagination.setTotalPages(rolePage.getTotalPages());
            for (Role role:rolePage
            ) {
                ReleaseResponse releaseResponse=new ReleaseResponse();
                BeanUtils.copyProperties(role,roleResponse);
                roleResponseList.add(roleResponse);
            }
            return roleResponseList;
        }
    }
}
