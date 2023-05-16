package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.PriorityResponse;
import com.defect.tracker.resquest.dto.PriorityRequest;
import com.defect.tracker.search.dto.PrioritySearch;
import org.springframework.data.domain.Pageable;

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


   public List<PriorityResponse> getPriorityPagination(Integer pageSize, Integer pageNumber, String sortPRoperty);


    List<PriorityResponse> multiSearchpriority(Pageable pageable, PaginatedContentResponse.Pagination pagination, PrioritySearch prioritySearch);
}
