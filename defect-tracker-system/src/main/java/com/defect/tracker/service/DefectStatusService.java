package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.DefectStatusResponse;
import com.defect.tracker.response.dto.DefectTypeResponse;
import com.defect.tracker.resquest.dto.DefecetStatusRequest;
import com.defect.tracker.search.dto.DefectStatusSearch;
import com.defect.tracker.search.dto.DefectTypeSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DefectStatusService {
    public void saveDefectStatus(DefecetStatusRequest defecetStatusRequest );

    public List<DefectStatusResponse> getAllDefectStatus();

    public boolean isDefectStatusExists(String name);

    public DefectStatusResponse getDefectStatusById(Long id);

    public boolean existByDefectStatus(Long id);

    public boolean isUpdatedDefectStatusNameExist(Long id, String name);

    public void deleteDefectStatus(Long id);

    public List<DefectStatusResponse> multiSearchDefectStatus(Pageable pageable, PaginatedContentResponse.Pagination pagination, DefectStatusSearch defectStatusSearch);

}
