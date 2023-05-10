package com.defect.tracker.service.impl;

import com.defect.tracker.entities.DefectType;
import com.defect.tracker.repositories.DefectTypeRepository;
import com.defect.tracker.response.dto.DefectTypeResponse;
import com.defect.tracker.resquest.dto.DefectTypeRequest;
import com.defect.tracker.service.DefectTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefectTypeServiceImpl implements DefectTypeService {
    @Autowired
    private DefectTypeRepository defectTypeRepository;
    @Override
    public void saveDefectType(DefectTypeRequest defectTypeRequest) {
        DefectType defectType=new DefectType();
        BeanUtils.copyProperties(defectTypeRequest,defectType);
        defectTypeRepository.save(defectType);

    }

    @Override
    public boolean isDefectTypeExits(String name) {
        return defectTypeRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public List<DefectTypeResponse> getAllDefectType() {
       List<DefectTypeResponse> defectTypeResponses=new ArrayList<>();
       List<DefectType> defectTypes=defectTypeRepository.findAll();
        for (DefectType defectType:defectTypes
             ) {
            DefectTypeResponse defectTypeResponse=new DefectTypeResponse();
            BeanUtils.copyProperties(defectType,defectTypeResponse);
            defectTypeResponses.add(defectTypeResponse);
        }
        return defectTypeResponses;
    }

    @Override
    public boolean existsByDefectType(Long id)
    {
        return defectTypeRepository.existsById(id);
    }
    @Override
    public boolean updateDefectTypeNameExits(String name,Long id)
    {
        return defectTypeRepository.existsByNameIgnoreCaseAndId(name,id);
    }

    @Override
    public DefectTypeResponse getDefectTypeById(Long id) {
        DefectTypeResponse defectTypeResponse=new DefectTypeResponse();
        DefectType defectType=defectTypeRepository.findById(id).get();
        BeanUtils.copyProperties(defectType,defectTypeResponse);

        return defectTypeResponse;
    }

    @Override
    public void deleteDefctType(Long id) {
        defectTypeRepository.deleteById(id);
    }
}
