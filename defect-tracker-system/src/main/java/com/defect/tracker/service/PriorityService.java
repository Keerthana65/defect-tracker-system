package com.defect.tracker.service;

import com.defect.tracker.entities.Priority;
import com.defect.tracker.response.dto.PriorityResponse;
import com.defect.tracker.resquest.dto.PriorityRequest;

import java.util.List;

public interface PriorityService {
    public void savePriority(PriorityRequest priorityRequest);

    public boolean isPriorityExistsByName(String name);

    public boolean isPriorityExistsByColor(String Color);

    public List<PriorityResponse> getAllPriority();

    public PriorityResponse getPriorityById(Long id);

    public boolean existsByPriority(long id);

    public boolean isUpdatePriorityNameExists(String name,Long id);

    public boolean isUpdatePriorityColorExists(String color,Long id);

    public void deletePriority(Long id);


}
