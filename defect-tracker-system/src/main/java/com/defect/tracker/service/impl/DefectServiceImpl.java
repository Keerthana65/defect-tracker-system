package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.*;
import com.defect.tracker.entities.QDefect;
import com.defect.tracker.repositories.DefectRepository;
import com.defect.tracker.response.dto.DefectResponse;
import com.defect.tracker.resquest.dto.DefectRequest;
import com.defect.tracker.search.dto.DefectSearch;
import com.defect.tracker.service.DefectService;
import com.defect.tracker.service.EmployeeService;
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
public class DefectServiceImpl implements DefectService {
    @Autowired
    private DefectRepository defectRepository;
    @Override
    public void saveDefect(DefectRequest defectRequest) {
        Defect defect=new Defect();
        Project project=new Project();
        project.setId(defectRequest.getProjectId());
        defect.setProject(project);

        Priority priority=new Priority();
        priority.setId(defectRequest.getPriorityId());
        defect.setPriority(priority);

        Seviarity seviarity=new Seviarity();
        seviarity.setId(defectRequest.getSeviarityId());
        defect.setSeviarity(seviarity);

        DefectType defectType=new DefectType();
        defectType.setId(defectRequest.getDefectTypeId());
        defect.setDefectType(defectType);

        DefectStatus defectStatus=new DefectStatus();
        defectStatus.setId(defectRequest.getDefectStatusId());
        defect.setDefectStatus(defectStatus);

        Employee employee=new Employee();
        employee.setId(defectRequest.getAssigToId());
        defect.setAssignTo(employee);

        Employee employee1=new Employee();
        employee1.setId(defectRequest.getReportId());
        defect.setReporter(employee1);

        Release release=new Release();
        release.setId(defectRequest.getReleaseId());
        defect.setRelease(release);

        BeanUtils.copyProperties(defectRequest,defect);
        defectRepository.save(defect);

    }

    @Override
    public boolean existsByName(String name) {
        return defectRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean existsByNameIgnoreCaseAndIdNot(String name, Long id) {

        return  defectRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public List<DefectResponse> getllDefect() {
        List<DefectResponse> defectResponses=new ArrayList<>();
        List<Defect> defects=defectRepository.findAll();
        for (Defect defect:defects) {
        DefectResponse defectResponse=new DefectResponse();
        defectResponse.setDefectTypeName(defect.getDefectType().getName());
        defectResponse.setDefectStatusName(defect.getDefectStatus().getName());
        defectResponse.setAssigToName(defect.getAssignTo().getFirName());
        defectResponse.setReportName(defect.getReporter().getFirName());
        defectResponse.setPriorityName(defect.getPriority().getName());
        defectResponse.setSeviarityName(defect.getSeviarity().getName());
        defectResponse.setProjectName(defect.getProject().getName());
        defectResponse.setReleaseName(defect.getRelease().getName());
        BeanUtils.copyProperties(defect,defectResponse);
        defectResponses.add(defectResponse);
        }
        return defectResponses;
    }

    @Override
    public DefectResponse getDefectById(Long id) {
        DefectResponse defectResponse = new DefectResponse();
        Defect defect = defectRepository.findById(id).get();
        defectResponse.setDefectTypeName(defect.getDefectType().getName());
        defectResponse.setDefectStatusName(defect.getDefectStatus().getName());
        defectResponse.setAssigToName(defect.getAssignTo().getFirName());
        defectResponse.setReportName(defect.getReporter().getFirName());
        defectResponse.setPriorityName(defect.getPriority().getName());
        defectResponse.setSeviarityName(defect.getSeviarity().getName());
        defectResponse.setProjectName(defect.getProject().getName());
        defectResponse.setReleaseName(defect.getRelease().getName());
        BeanUtils.copyProperties(defect, defectResponse);
        return defectResponse;
    }
    @Override
    public void deleteDefect(Long id) {
        defectRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return defectRepository.existsById(id);
    }

    @Override
    public List<DefectResponse> multiSearchDefect(Pageable pageable, PaginatedContentResponse.Pagination pagination, DefectSearch defectSearch) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();

        if(Utils.isNotNullAndEmpty(defectSearch.getDefectName()))
        {
            booleanBuilder.and(QDefect.defect.name.eq(defectSearch.getDefectName()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getDefectDescription()))
        {
            booleanBuilder.and(QDefect.defect.description.eq(defectSearch.getDefectDescription()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getDefectStepToRecreat()))
        {
            booleanBuilder.and(QDefect.defect.stepToRecreat.eq(defectSearch.getDefectStepToRecreat()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getDefectTypeName()))
        {
            booleanBuilder.and(QDefect.defect.defectType.name.eq(defectSearch.getDefectTypeName()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getAssigntoName()))
        {
            booleanBuilder.and(QDefect.defect.assignTo.firName.eq(defectSearch.getAssigntoName()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getDefectStatusName()))
        {
            booleanBuilder.and(QDefect.defect.defectStatus.name.eq(defectSearch.getDefectStatusName()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getReportToname()))
        {
            booleanBuilder.and(QDefect.defect.reporter.firName.eq(defectSearch.getReportToname()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getPriorityName()))
        {
            booleanBuilder.and(QDefect.defect.priority.name.eq(defectSearch.getPriorityName()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getPriorityColor()))
        {
            booleanBuilder.and(QDefect.defect.priority.color.eq(defectSearch.getPriorityColor()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getProjectName()))
        {
            booleanBuilder.and(QDefect.defect.project.name.eq(defectSearch.getProjectName()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getSeviarityName()))
        {
            booleanBuilder.and(QDefect.defect.seviarity.name.eq(defectSearch.getSeviarityName()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getSeviarityColor()))
        {
            booleanBuilder.and(QDefect.defect.seviarity.color.eq(defectSearch.getSeviarityColor()));
        }
        if(Utils.isNotNullAndEmpty(defectSearch.getReleaseName()))
        {
            booleanBuilder.and(QDefect.defect.release.name.eq(defectSearch.getReleaseName()));
        }

        List<DefectResponse> defectResponses=new ArrayList<>();
        Page<Defect> defects=defectRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(defects.getTotalElements());
        pagination.setTotalPages(defects.getTotalPages());
        for (Defect defect:defects) {
            DefectResponse defectResponse=new DefectResponse();
            defectResponse.setDefectTypeName(defect.getDefectType().getName());
            defectResponse.setDefectStatusName(defect.getDefectStatus().getName());
            defectResponse.setAssigToName(defect.getAssignTo().getFirName());
            defectResponse.setReportName(defect.getReporter().getFirName());
            defectResponse.setPriorityName(defect.getPriority().getName());
            defectResponse.setSeviarityName(defect.getSeviarity().getName());
            defectResponse.setProjectName(defect.getProject().getName());
            defectResponse.setReleaseName(defect.getRelease().getName());
            BeanUtils.copyProperties(defect,defectResponse);
            defectResponses.add(defectResponse);
        }
        return defectResponses;
    }

}
