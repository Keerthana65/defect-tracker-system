package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.DefectStatus;
import com.defect.tracker.entities.QDefectStatus;
import com.defect.tracker.entities.DefectType;
import com.defect.tracker.entities.Designation;
import com.defect.tracker.repositories.DefectStatusRepository;
import com.defect.tracker.response.dto.DefectStatusResponse;
import com.defect.tracker.response.dto.DefectTypeResponse;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.resquest.dto.DefecetStatusRequest;
import com.defect.tracker.search.dto.DefectStatusSearch;
import com.defect.tracker.search.dto.DefectTypeSearch;
import com.defect.tracker.service.DefectStatusService;
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
    @Override
    public List<DefectStatusResponse> multiSearchDefectStatus(Pageable pageable, PaginatedContentResponse.Pagination pagination, DefectStatusSearch defectStatusSearch) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(defectStatusSearch.getDefectStatusName()))
        {
            booleanBuilder.and(QDefectStatus.defectStatus.name.eq(defectStatusSearch.getDefectStatusName()));
        }
        List<DefectStatusResponse> defectStatusResponseList=new ArrayList<>();
        Page<DefectStatus> defectStatusPage=defectStatusRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(defectStatusPage.getTotalElements());
        pagination.setTotalPages(defectStatusPage.getTotalPages());
        for (DefectStatus defectStatus:defectStatusPage
        ) {
            DefectStatusResponse defectStatusResponse=new DefectStatusResponse();
            BeanUtils.copyProperties(defectStatus,defectStatusResponse);
            defectStatusResponseList.add(defectStatusResponse);
        }
        return defectStatusResponseList;
    }
}
