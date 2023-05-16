package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Priority;
import com.defect.tracker.entities.QPriority;
import com.defect.tracker.repositories.PriorityRepository;
import com.defect.tracker.response.dto.PriorityResponse;
import com.defect.tracker.resquest.dto.PriorityRequest;
import com.defect.tracker.search.dto.PrioritySearch;
import com.defect.tracker.service.PriorityService;
import com.defect.tracker.utils.Utils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {
    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public void savePriority(PriorityRequest priorityRequest) {
        Priority priority = new Priority();
        BeanUtils.copyProperties(priorityRequest, priority);
        priorityRepository.save(priority);
    }

    @Override
    public boolean isPriorityExistsByName(String name) {

        return priorityRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean isPriorityExistsByColor(String color) {
        return priorityRepository.existsByColorIgnoreCase(color);
    }

    @Override
    public List<PriorityResponse> getAllPriority() {
        List<Priority> priorities = priorityRepository.findAll();
        List<PriorityResponse> priorityResponses = new ArrayList<>();
        for (Priority priority : priorities
        ) {
            PriorityResponse priorityResponse = new PriorityResponse();
            BeanUtils.copyProperties(priority, priorityResponse);
            priorityResponses.add(priorityResponse);
        }
        return priorityResponses;
    }

    @Override
    public PriorityResponse getPriorityById(Long id) {
        Priority priority = priorityRepository.findById(id).get();
        PriorityResponse priorityResponse = new PriorityResponse();
        BeanUtils.copyProperties(priority, priorityResponse);
        return priorityResponse;
    }

    @Override
    public boolean existsByPriority(long id) {
        return priorityRepository.existsById(id);
    }

    @Override
    public boolean isUpdatePriorityNameExists(String name, Long id) {
        return priorityRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    @Override
    public boolean isUpdatePriorityColorExists(String color, Long id) {
        return priorityRepository.existsByColorIgnoreCaseAndIdNot(color, id);
    }

    @Override
    public void deletePriority(Long id) {
        priorityRepository.deleteById(id);
    }

    @Override
    public List<PriorityResponse> getPriorityPagination(Integer pageSize, Integer pageNumber, String sortPRoperty) {
        Pageable pageable = null;
        if (sortPRoperty == null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "name");

        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortPRoperty);
        }
        List<PriorityResponse> priorityResponses = new ArrayList<>();
        Page<Priority> priorities = priorityRepository.findAll(pageable);
        for (Priority priority : priorities
        ) {
            PriorityResponse priorityResponse = new PriorityResponse();
            BeanUtils.copyProperties(priority, priorityResponse);
            priorityResponses.add(priorityResponse);
        }
        return priorityResponses;
    }

    @Override
    public List<PriorityResponse> multiSearchpriority(Pageable pageable, PaginatedContentResponse.Pagination pagination, PrioritySearch prioritySearch) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(prioritySearch.getPriorityName()))
        {
            booleanBuilder.and(QPriority.priority.name.eq(prioritySearch.getPriorityName()));
        }
        if(Utils.isNotNullAndEmpty(prioritySearch.getPriorityColor()))
        {
            booleanBuilder.and(QPriority.priority.color.eq(prioritySearch.getPriorityColor()));
        }
        List<PriorityResponse> priorityResponseList=new ArrayList<>();
        Page<Priority> priorityList = priorityRepository.findAll(booleanBuilder, pageable);
        pagination.setTotalRecords(priorityList.getTotalElements());
        pagination.setPageSize(priorityList.getTotalPages());
        for (Priority priority:priorityList
             ) {
            PriorityResponse priorityResponse=new PriorityResponse();
            BeanUtils.copyProperties(priority,priorityResponse);
            priorityResponseList.add(priorityResponse);
        }
        return priorityResponseList;

    }


}
