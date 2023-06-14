package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Project;
import com.defect.tracker.entities.ProjectStatus;
import com.defect.tracker.entities.QProject;
import com.defect.tracker.repositories.ProjectRepository;
import com.defect.tracker.response.dto.ProjectResponse;
import com.defect.tracker.resquest.dto.ProjectRequest;
import com.defect.tracker.search.dto.ProjectSearch;
import com.defect.tracker.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public void saveProject(ProjectRequest projectRequest) {
        Project project=new Project();
        ProjectStatus projectStatus=new ProjectStatus();
        projectStatus.setId(projectRequest.getProjectStatusId());
        project.setProjectStatus(projectStatus);
        BeanUtils.copyProperties(projectRequest,project);
        projectRepository.save(project);
    }

    @Override
    public boolean exisByName(String name) {
        return projectRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean existsByProjectNameAndIdNot(String name, Long id) {
        return projectRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public List<ProjectResponse> getAllProject() {
        List<ProjectResponse> projectResponses=new ArrayList<>();
        List<Project> projects=projectRepository.findAll();
        for (Project project:projects
             ) {
            ProjectResponse projectResponse=new ProjectResponse();
            projectResponse.setProjectStatusName(project.getProjectStatus().getName());
            BeanUtils.copyProperties(project,projectResponse);
            projectResponses.add(projectResponse);
        }
        return projectResponses;
    }
    @Override
    public ProjectResponse getProjectById(Long id)
    {
        ProjectResponse projectResponse=new ProjectResponse();
        Project project=projectRepository.findById(id).get();
        projectResponse.setProjectStatusName(project.getProjectStatus().getName());
        BeanUtils.copyProperties(project,projectResponse);
        return projectResponse;

    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectResponse> multiSearchProject(Pageable pageable, PaginatedContentResponse.Pagination pagination, ProjectSearch projectSearch) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(projectSearch.getProjectStatusName()))
        {
            booleanBuilder.and(QProject.project.projectStatus.name.eq(projectSearch.getProjectStatusName()));
        }
        if(Utils.isNotNullAndEmpty(projectSearch.getContactNumber()))
        {
            booleanBuilder.and(QProject.project.contactNumber.eq(Long.parseLong(projectSearch.getContactNumber())));
        }
        if(Utils.isNotNullAndEmpty(projectSearch.getContactPerson()))
        {
            booleanBuilder.and(QProject.project.contactPerson.eq(projectSearch.getContactPerson()));
        }
        if(Utils.isNotNullAndEmpty(projectSearch.getName()))
        {
            booleanBuilder.and(QProject.project.name.eq(projectSearch.getName()));
        }

        List<ProjectResponse> projectResponses=new ArrayList<>();
        Page<Project> projectPage=projectRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(projectPage.getTotalElements());
        pagination.setTotalPages(projectPage.getTotalPages());
        for (Project project:projectPage
        ) {
            ProjectResponse projectResponse=new ProjectResponse();
            projectResponse.setProjectStatusName(project.getProjectStatus().getName());
            BeanUtils.copyProperties(project,projectResponse);
            projectResponses.add(projectResponse);
        }
        return projectResponses;
    }
}
