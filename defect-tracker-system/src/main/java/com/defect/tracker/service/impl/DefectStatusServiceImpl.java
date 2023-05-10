package com.defect.tracker.service.impl;

import com.defect.tracker.entities.DefectStatus;
import com.defect.tracker.entities.Designation;
import com.defect.tracker.repositories.DefectStatusRepository;
import com.defect.tracker.response.dto.DefectStatusResponse;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.resquest.dto.DefecetStatusRequest;
import com.defect.tracker.service.DefectStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefectStatusServiceImpl implements DefectStatusService {
    @Autowired
    private DefectStatusRepository defectStatusRepository;
    @Override
    public void saveDefectStatus(DefecetStatusRequest defecetStatusRequest) {
        DefectStatus defectStatus  = new DefectStatus();
        BeanUtils.copyProperties(defecetStatusRequest, defectStatus);
        defectStatusRepository.save(defectStatus);
    }

    @Override
    public List<DefectStatusResponse> getAllDefectStatus() {
        List<DefectStatusResponse> defectStatusResponses = new ArrayList<>();
        List<DefectStatus> defectStatuses = defectStatusRepository.findAll();
        for (DefectStatus defectStatus : defectStatuses) {
            DefectStatusResponse defectStatusResponse = new DefectStatusResponse();
            BeanUtils.copyProperties(defectStatus,defectStatusResponse );
            defectStatusResponses.add(defectStatusResponse);
        }
        return defectStatusResponses;
    }

    @Override
    public boolean isDefectStatusExists(String name) {
        return defectStatusRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public DefectStatusResponse getDefectStatusById(Long id) {
      DefectStatus defectStatus = defectStatusRepository.findById(id).get();
        DefectStatusResponse defectStatusResponse= new DefectStatusResponse();
        BeanUtils.copyProperties(defectStatus,defectStatusResponse );
        return defectStatusResponse;

    }

    @Override
    public boolean existByDefectStatus(Long id) {
        return defectStatusRepository.existsById(id);
    }

    @Override
    public boolean isUpdatedDefectStatusNameExist(Long id, String name) {
        return defectStatusRepository.existsByNameIgnoreCaseAndId(name,id);
    }

    @Override
    public void deleteDefectStatus(Long id) {
       defectStatusRepository.deleteById(id);
    }
}
