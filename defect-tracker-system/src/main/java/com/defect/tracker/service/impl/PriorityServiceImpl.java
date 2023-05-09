package com.defect.tracker.service.impl;

import com.defect.tracker.entities.Priority;
import com.defect.tracker.repositories.PriorityRepository;
import com.defect.tracker.response.dto.PriorityResponse;
import com.defect.tracker.resquest.dto.PriorityRequest;
import com.defect.tracker.service.PriorityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        return priorityRepository.existsByColorIgnoreCaseAndIdNot(color,id);
    }

    @Override
    public void deletePriority(Long id) {
        priorityRepository.deleteById(id);
    }
}
