package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.SeviarityResponse;
import com.defect.tracker.resquest.dto.SeviarityRequest;
import com.defect.tracker.search.dto.PrioritySearch;
import com.defect.tracker.search.dto.SeviaritySearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SeviarityService {

    public void saveSeviarity(SeviarityRequest seviarityRequest);

    public boolean isSeviarityExistsByName(String name);

    public boolean isSeviarityExistsByColor(String color);

    public List<SeviarityResponse> getAllSeviarity();

    public SeviarityResponse getSeviarityById(Long id);

    public boolean existsByPriority(Long id);

    public void deleteSeviarity(Long id);

 public boolean isUpdateSeviarityExistsByName(String name,Long id);

    public boolean isUpdateSeviarityExistsByColor(String color,Long id);

    public List<SeviarityResponse> multiSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, SeviaritySearch seviaritySearch);

}
