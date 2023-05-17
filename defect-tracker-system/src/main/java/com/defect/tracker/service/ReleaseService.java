package com.defect.tracker.service;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.response.dto.ReleaseResponse;
import com.defect.tracker.resquest.dto.DesignationRequest;
import com.defect.tracker.resquest.dto.ReleaseRequest;
import com.defect.tracker.search.dto.ReleaseSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReleaseService {
    public void saveRelease(ReleaseRequest releaseRequest);

    public List<ReleaseResponse> getAllRelease();

    public boolean isReleaseExists(String name);

    public ReleaseResponse getReleaseById(Long id);

    public boolean existByRelease(Long id);

    public boolean isUpdatedReleaseNameExist(Long id, String name);

    public void deleteRelease(Long id);

    public List<ReleaseResponse> multiSearchRealese(Pageable pageable, PaginatedContentResponse.Pagination pagination, ReleaseSearch releaseSearch);
}
