package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.DefectType;
import com.defect.tracker.response.dto.DefectTypeResponse;
import com.defect.tracker.resquest.dto.DefectTypeRequest;
import com.defect.tracker.search.dto.DefectTypeSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DefectTypeService {

    public void saveDefectType(@RequestBody DefectTypeRequest defectTypeRequest);

    public boolean isDefectTypeExits(String name);

    public boolean updateDefectTypeNameExits(String name,Long id);

    public boolean existsByDefectType(Long id);

    public List<DefectTypeResponse> getAllDefectType();

    public DefectTypeResponse getDefectTypeById(Long id);

    public void deleteDefctType(Long id);

    public List<DefectTypeResponse> multiSearchDefectType(Pageable pageable, PaginatedContentResponse.Pagination pagination, DefectTypeSearch defectTypeSearch);

}
