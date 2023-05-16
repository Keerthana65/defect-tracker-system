package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.QSeviarity;
import com.defect.tracker.entities.Seviarity;
import com.defect.tracker.repositories.SeviarityRepository;
import com.defect.tracker.response.dto.SeviarityResponse;
import com.defect.tracker.resquest.dto.SeviarityRequest;
import com.defect.tracker.search.dto.PrioritySearch;
import com.defect.tracker.search.dto.SeviaritySearch;
import com.defect.tracker.service.SeviarityService;
import com.defect.tracker.utils.Utils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeviarityServiceImpl implements SeviarityService {

    @Autowired
    private SeviarityRepository seviarityRepository;

    @Override
    public void saveSeviarity(SeviarityRequest seviarityRequest) {
        Seviarity seviarity = new Seviarity();
        BeanUtils.copyProperties(seviarityRequest, seviarity);
        seviarityRepository.save(seviarity);
    }

    @Override
    public boolean isSeviarityExistsByName(String name) {
        return seviarityRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean isSeviarityExistsByColor(String color) {
        return seviarityRepository.existsByColorIgnoreCase(color);
    }

    @Override
    public List<SeviarityResponse> getAllSeviarity() {
        List<SeviarityResponse> seviarityResponses = new ArrayList<>();
        List<Seviarity> seviarities = seviarityRepository.findAll();
        for (Seviarity seviarity : seviarities
        ) {
            SeviarityResponse seviarityResponse = new SeviarityResponse();
            BeanUtils.copyProperties(seviarity, seviarityResponse);
            seviarityResponses.add(seviarityResponse);
        }
        return seviarityResponses;
    }

    @Override
    public SeviarityResponse getSeviarityById(Long id) {
        SeviarityResponse seviarityResponse = new SeviarityResponse();
        Seviarity seviarity = seviarityRepository.findById(id).get();
        BeanUtils.copyProperties(seviarity, seviarityResponse);
        return seviarityResponse;
    }

    @Override
    public void deleteSeviarity(Long id) {
        seviarityRepository.deleteById(id);
    }

    @Override
    public boolean isUpdateSeviarityExistsByName(String name, Long id) {
        return seviarityRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public boolean isUpdateSeviarityExistsByColor(String color, Long id) {
        return seviarityRepository.existsByColorIgnoreCaseAndIdNot(color, id);
    }



    @Override
    public List<SeviarityResponse> multiSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, SeviaritySearch seviaritySearch) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (Utils.isNotNullAndEmpty(seviaritySearch.getSeviarityColor())) {
            booleanBuilder.and(QSeviarity.seviarity.color.eq(seviaritySearch.getSeviarityColor()));
        }
        if (Utils.isNotNullAndEmpty(seviaritySearch.getSeviarityName())) {
            booleanBuilder.and(QSeviarity.seviarity.name.eq(seviaritySearch.getSeviarityName()));
        }
        List<SeviarityResponse> seviarityResponseList = new ArrayList<>();

        Page<Seviarity> seviarities = seviarityRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(seviarities.getTotalElements());
        pagination.setTotalPages(seviarities.getTotalPages());
        for (Seviarity seviarity : seviarities
        ) {
            SeviarityResponse seviarityResponse = new SeviarityResponse();
            BeanUtils.copyProperties(seviarity, seviarityResponse);
            seviarityResponseList.add(seviarityResponse);
        }
        return seviarityResponseList;
    }


    @Override
    public boolean existsByPriority(Long id) {
        return seviarityRepository.existsById(id);
    }
}
