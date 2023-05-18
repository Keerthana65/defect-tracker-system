package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.QSeverity;
import com.defect.tracker.entities.Severity;
import com.defect.tracker.repositories.SeverityRepository;
import com.defect.tracker.response.dto.SeverityResponse;
import com.defect.tracker.resquest.dto.SeverityRequest;
import com.defect.tracker.search.dto.SeveritySearch;
import com.defect.tracker.service.SeverityService;
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
public class SeverityServiceImpl implements SeverityService {

    @Autowired
    private SeverityRepository seviarityRepository;

    @Override
    public void saveSeverity(SeverityRequest severityRequest) {
        Severity severity = new Severity();
        BeanUtils.copyProperties(severityRequest, severity);
        seviarityRepository.save(severity);
    }

    @Override
    public boolean isSeverityExistsByName(String name) {
        return seviarityRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean isSeverityExistsByColor(String color) {
        return seviarityRepository.existsByColorIgnoreCase(color);
    }

    @Override
    public List<SeverityResponse> getAllSeverity() {
        List<SeverityResponse> severityResponses = new ArrayList<>();
        List<Severity> severities = seviarityRepository.findAll();
        for (Severity severity : severities
        ) {
            SeverityResponse severityResponse = new SeverityResponse();
            BeanUtils.copyProperties(severity, severityResponse);
            severityResponses.add(severityResponse);
        }
        return severityResponses;
    }

    @Override
    public SeverityResponse getSeverityById(Long id) {
        SeverityResponse severityResponse = new SeverityResponse();
        Severity severity = seviarityRepository.findById(id).get();
        BeanUtils.copyProperties(severity, severityResponse);
        return severityResponse;
    }

    @Override
    public void deleteSeverity(Long id) {
        seviarityRepository.deleteById(id);
    }

    @Override
    public boolean isUpdateSeverityExistsByName(String name, Long id) {
        return seviarityRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public boolean isUpdateSeverityExistsByColor(String color, Long id) {
        return seviarityRepository.existsByColorIgnoreCaseAndIdNot(color, id);
    }



    @Override
    public List<SeverityResponse> multiSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, SeveritySearch severitySearch) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (Utils.isNotNullAndEmpty(severitySearch.getSeviarityColor())) {
            booleanBuilder.and(QSeverity.severity.color.eq(severitySearch.getSeviarityColor()));
        }
        if (Utils.isNotNullAndEmpty(severitySearch.getSeviarityName())) {
            booleanBuilder.and(QSeverity.severity.name.eq(severitySearch.getSeviarityName()));
        }
        List<SeverityResponse> seviarityResponseList = new ArrayList<>();

        Page<Severity> severities = seviarityRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(severities.getTotalElements());
        pagination.setTotalPages(severities.getTotalPages());
        for (Severity severity : severities
        ) {
            SeverityResponse severityResponse = new SeverityResponse();
            BeanUtils.copyProperties(severity, severityResponse);
            seviarityResponseList.add(severityResponse);
        }
        return seviarityResponseList;
    }


    @Override
    public boolean existsByPriority(Long id) {
        return seviarityRepository.existsById(id);
    }
}
