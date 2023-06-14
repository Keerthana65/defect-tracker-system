package com.defect.tracker.service;

import java.util.List;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.DefectStatusResponse;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.response.dto.ProjectStatusResponse;
import com.defect.tracker.resquest.dto.DesignationRequest;
import com.defect.tracker.search.dto.DefectStatusSearch;
import com.defect.tracker.search.dto.DesiginationSearch;
import org.springframework.data.domain.Pageable;

public interface DesignationService {
  public void saveDesignation(DesignationRequest designationRequest);

  public List<DesignationResponse> getAllDesignation();

  public boolean isDesignationExists(String name);

  public DesignationResponse getDesignationById(Long id);

  public boolean existByDesignation(Long id);

  public boolean isUpdatedDesignationNameExist(Long id, String name);

  public void deleteDesignation(Long id);

  public List<DesignationResponse> multiSearchDesignationSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, DesiginationSearch desiginationSearch);
}
