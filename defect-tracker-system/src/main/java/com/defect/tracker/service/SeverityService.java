package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.SeverityResponse;
import com.defect.tracker.resquest.dto.SeverityRequest;
import com.defect.tracker.search.dto.SeveritySearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SeverityService {

    public void saveSeverity(SeverityRequest severityRequest);


    public boolean isSeverityExistsByName(String name);

    public boolean isSeverityExistsByColor(String color);

    public List<SeverityResponse> getAllSeverity();

    public SeverityResponse getSeverityById(Long id);

    public boolean existsByPriority(Long id);

    public void deleteSeverity(Long id);

 public boolean isUpdateSeverityExistsByName(String name,Long id);

    public boolean isUpdateSeverityExistsByColor(String color,Long id);

    public List<SeverityResponse> multiSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, SeveritySearch severitySearch);

}
