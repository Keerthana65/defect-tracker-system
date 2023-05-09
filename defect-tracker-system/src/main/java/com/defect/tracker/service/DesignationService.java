package com.defect.tracker.service;

import java.util.List;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.resquest.dto.DesignationRequest;

public interface DesignationService {
  public void saveDesignation(DesignationRequest designationRequest);

  public List<DesignationResponse> getAllDesignation();

  public boolean isDesignationExists(String name);

  public DesignationResponse getDesignationById(Long id);

  public boolean existByDesignation(Long id);

  public boolean isUpdatedDesignationNameExist(Long id, String name);

  public void deleteDesignation(Long id);
}
