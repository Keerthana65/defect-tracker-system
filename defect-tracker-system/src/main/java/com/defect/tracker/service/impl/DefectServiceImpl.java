package com.defect.tracker.service.impl;

import com.defect.tracker.entities.*;
import com.defect.tracker.repositories.DefectRepository;
import com.defect.tracker.response.dto.DefectResponse;
import com.defect.tracker.resquest.dto.DefectRequest;
import com.defect.tracker.service.DefectService;
import com.defect.tracker.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

}
