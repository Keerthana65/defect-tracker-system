package com.defect.tracker.service;

import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.response.dto.ReleaseResponse;
import com.defect.tracker.resquest.dto.DesignationRequest;
import com.defect.tracker.resquest.dto.ReleaseRequest;

import java.util.List;

public interface ReleaseService {
    public void saveRelease(ReleaseRequest releaseRequest);

    public List<ReleaseResponse> getAllRelease();

    public boolean isReleaseExists(String name);

    public ReleaseResponse getReleaseById(Long id);

    public boolean existByRelease(Long id);

    public boolean isUpdatedReleaseNameExist(Long id, String name);

    public void deleteRelease(Long id);
}
