package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Employee;
import com.defect.tracker.entities.Project;
import com.defect.tracker.entities.ProjectAllocation;
import com.defect.tracker.entities.QProjectAllocation;
import com.defect.tracker.entities.Role;
import com.defect.tracker.repositories.ProjectAllocationRepository;
import com.defect.tracker.response.dto.ProjectAllocationResponse;
import com.defect.tracker.resquest.dto.ProjectAllocationRequest;
import com.defect.tracker.search.dto.ProjectAllocationSearch;
import com.defect.tracker.service.ProjectAllocationService;
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
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
    @Autowired
    private ProjectAllocationRepository projectAllocationRepository;

    @Override
    public void saveProjectAllocation(ProjectAllocationRequest projectAllocationRequest) {
        ProjectAllocation projectAllocation=new ProjectAllocation();
        Project project=new Project();
        project.setId(projectAllocationRequest.getProjectId());
        projectAllocation.setProject(project);

        Employee employee=new Employee();
        employee.setId(projectAllocationRequest.getEmployeeId());
        projectAllocation.setEmployee(employee);

        Role role=new Role();
        role.setId(projectAllocationRequest.getRoleId());
        projectAllocation.setRole(role);

        BeanUtils.copyProperties(projectAllocationRequest,projectAllocation);
        projectAllocationRepository.save(projectAllocation);
    }

    @Override
    public List<ProjectAllocationResponse> getAllProjectAllocation() {
        List<ProjectAllocationResponse> projectAllocationResponses=new ArrayList<>();
        List<ProjectAllocation> projectAllocations=projectAllocationRepository.findAll();
        for (ProjectAllocation projectAllocation:projectAllocations
             ) {
            ProjectAllocationResponse projectAllocationResponse=new ProjectAllocationResponse();
            projectAllocationResponse.setProjectName(projectAllocation.getProject().getName());
            projectAllocationResponse.setEmployeeFirName(projectAllocation.getEmployee().getFirName());
            projectAllocationResponse.setRoleName(projectAllocation.getRole().getName());
            BeanUtils.copyProperties(projectAllocation,projectAllocationResponse);
            projectAllocationResponses.add(projectAllocationResponse);
        }
        return projectAllocationResponses;
    }

    @Override
    public ProjectAllocationResponse getProjectAllocationById(Long id) {
        ProjectAllocationResponse projectAllocationResponse=new ProjectAllocationResponse();
        ProjectAllocation projectAllocation=projectAllocationRepository.findById(id).get();
        projectAllocationResponse.setProjectName(projectAllocation.getProject().getName());
        projectAllocationResponse.setEmployeeFirName(projectAllocation.getEmployee().getFirName());
        projectAllocationResponse.setRoleName(projectAllocation.getRole().getName());
        BeanUtils.copyProperties(projectAllocation,projectAllocationResponse);
        return projectAllocationResponse;
    }

    @Override
    public void deletePRojectAllocation(Long id) {
        projectAllocationRepository.deleteById(id);
    }

    @Override
    public List<ProjectAllocationResponse> multiSearchProjectAllocation(Pageable pageable,
                                                                        PaginatedContentResponse.Pagination pagination,
                                                                        ProjectAllocationSearch projectAllocationSearch) {
       BooleanBuilder booleanBuilder=new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(projectAllocationSearch.getProjectName()))
        {
            booleanBuilder.and(QProjectAllocation.projectAllocation.project.name.eq(projectAllocationSearch.getProjectName()));
        }
        if(Utils.isNotNullAndEmpty(projectAllocationSearch.getRoleName()))
        {
            booleanBuilder.and(QProjectAllocation.projectAllocation.role.name.eq(projectAllocationSearch.getRoleName()));
        }
        if(Utils.isNotNullAndEmpty(projectAllocationSearch.getEmployeeFirName()))
        {
            booleanBuilder.and(QProjectAllocation.projectAllocation.employee.firName.eq(projectAllocationSearch.getEmployeeFirName()));
        }

        List<ProjectAllocationResponse> projectAllocationResponses=new ArrayList<>();
        Page<ProjectAllocation> projectAllocationPage=projectAllocationRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(projectAllocationPage.getTotalElements());
        pagination.setTotalPages(projectAllocationPage.getTotalPages());
        for (ProjectAllocation projectAllocation:projectAllocationPage
        ) {
            ProjectAllocationResponse projectAllocationResponse=new ProjectAllocationResponse();
            projectAllocationResponse.setProjectName(projectAllocation.getProject().getName());
            projectAllocationResponse.setEmployeeFirName(projectAllocation.getEmployee().getFirName());
            projectAllocationResponse.setRoleName(projectAllocation.getRole().getName());
            BeanUtils.copyProperties(projectAllocation,projectAllocationResponse);
            projectAllocationResponses.add(projectAllocationResponse);
        }
        return projectAllocationResponses;

    }



    @Override
    public boolean existsById(Long id)
    {
        return projectAllocationRepository.existsById(id);
    }
}
