package com.defect.tracker.service;

import com.defect.tracker.entities.Defect;
import com.defect.tracker.response.dto.DefectResponse;
import com.defect.tracker.resquest.dto.DefectRequest;

import java.util.List;

public interface DefectService {
    public void saveDefect(DefectRequest defectRequest);
    public boolean existsByName(String name);

    public boolean existsByNameIgnoreCaseAndIdNot(String name,Long id);
    public List<DefectResponse> getllDefect();

    public DefectResponse getDefectById(Long id);

    public void deleteDefect(Long id);

    public boolean existById(Long id);
}
