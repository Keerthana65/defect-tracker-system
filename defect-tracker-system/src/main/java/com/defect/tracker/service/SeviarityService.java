package com.defect.tracker.service;

import com.defect.tracker.response.dto.SeviarityResponse;
import com.defect.tracker.resquest.dto.SeviarityRequest;

import java.util.List;

public interface SeviarityService {

    public void saveSeviarity(SeviarityRequest seviarityRequest);

    public boolean isSeviarityExistsByName(String name);

    public boolean isSeviarityExistsByColor(String color);

    public List<SeviarityResponse> getAllSeviarity();

    public SeviarityResponse getSeviarityById(Long id);

    public boolean existsByPriority(Long id);

    public void deleteSeviarity(Long id);


}
