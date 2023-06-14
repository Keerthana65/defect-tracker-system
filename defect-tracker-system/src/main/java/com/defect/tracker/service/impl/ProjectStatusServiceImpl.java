package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.ProjectStatus;
import com.defect.tracker.entities.QProjectStatus;
import com.defect.tracker.repositories.ProjectStatusRepository;
import com.defect.tracker.response.dto.ProjectStatusResponse;
import com.defect.tracker.resquest.dto.ProjectStatusRequest;
import com.defect.tracker.search.dto.ProjectStatusSearch;
import com.defect.tracker.service.ProjectStatusService;
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
public class ProjectStatusServiceImpl implements ProjectStatusService {

    @Autowired
    private ProjectStatusRepository projectStatusRepository;

    @Override
    public void saveProjectStatus(ProjectStatusRequest projectStatusRequest) {
        ProjectStatus projectStatus = new ProjectStatus();
        BeanUtils.copyProperties(projectStatusRequest, projectStatus);
        projectStatusRepository.save(projectStatus);
    }

    @Override
    public List<ProjectStatusResponse> getAllProjectStatus() {
        List<ProjectStatusResponse> projectStatusResponses = new ArrayList<>();
        List<ProjectStatus> projectStatuses = projectStatusRepository.findAll();
        for (ProjectStatus projectStatus : projectStatuses) {
            ProjectStatusResponse projectStatusResponse = new ProjectStatusResponse();
            BeanUtils.copyProperties(projectStatus, projectStatusResponse);
            projectStatusResponses.add(projectStatusResponse);
        }
        return projectStatusResponses;
    }

    @Override
    public boolean isProjectStatusExists(String name) {
        return projectStatusRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public ProjectStatusResponse getProjectStatusById(Long id) {
        ProjectStatus projectStatus = projectStatusRepository.findById(id).get();
        ProjectStatusResponse projectStatusResponse = new ProjectStatusResponse();
        BeanUtils.copyProperties(projectStatus, projectStatusResponse);
        return projectStatusResponse;
    }

    @Override
    public boolean existByProjectStatus(Long id) {
        return projectStatusRepository.existsById(id);
    }

    @Override
    public boolean isUpdatedProjectStatusNameExist(Long id, String name) {
        return projectStatusRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public void deleteProjectStatus(Long id) {
        projectStatusRepository.deleteById(id);
    }


    @Override
    public List<ProjectStatusResponse> multiSearchProjectStatusSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, ProjectStatusSearch projectStatusSearch) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(projectStatusSearch.getProjectStatusName()))
        {
            booleanBuilder.and(QProjectStatus.projectStatus.name.eq(projectStatusSearch.getProjectStatusName()));
        }
        List<ProjectStatusResponse> projectStatusResponseList=new ArrayList<>();
        Page<ProjectStatus> projectStatusPage=projectStatusRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(projectStatusPage.getTotalElements());
        pagination.setTotalPages(projectStatusPage.getTotalPages());
        for (ProjectStatus projectStatus:projectStatusPage
        ) {
            ProjectStatusResponse projectStatusResponse=new ProjectStatusResponse();
            BeanUtils.copyProperties(projectStatus,projectStatusResponse);
            projectStatusResponseList.add(projectStatusResponse);
        }
        return projectStatusResponseList;
    }
}
