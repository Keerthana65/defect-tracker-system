package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.DefectType;
import com.defect.tracker.entities.QDefectType;
import com.defect.tracker.repositories.DefectTypeRepository;
import com.defect.tracker.response.dto.DefectTypeResponse;
import com.defect.tracker.resquest.dto.DefectTypeRequest;
import com.defect.tracker.search.dto.DefectTypeSearch;
import com.defect.tracker.service.DefectTypeService;
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

    @Override
    public List<DefectTypeResponse> multiSearchDefectType(Pageable pageable, PaginatedContentResponse.Pagination pagination, DefectTypeSearch defectTypeSearch) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(defectTypeSearch.getDefectTypeName()))
        {
            booleanBuilder.and(QDefectType.defectType.name.eq(defectTypeSearch.getDefectTypeName()));
        }
        List<DefectTypeResponse> defectTypeResponseList=new ArrayList<>();
        Page<DefectType> defectTypePage=defectTypeRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(defectTypePage.getTotalElements());
        pagination.setTotalPages(defectTypePage.getTotalPages());
        for (DefectType defectType:defectTypePage
             ) {
            DefectTypeResponse defectTypeResponse=new DefectTypeResponse();
            BeanUtils.copyProperties(defectType,defectTypeResponse);
            defectTypeResponseList.add(defectTypeResponse);
        }
        return defectTypeResponseList;
    }
}
