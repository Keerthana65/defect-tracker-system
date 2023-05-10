package com.defect.tracker.service.impl;

import com.defect.tracker.entities.Designation;
import com.defect.tracker.entities.Release;
import com.defect.tracker.repositories.ReleaseRepository;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.response.dto.ReleaseResponse;
import com.defect.tracker.resquest.dto.ReleaseRequest;
import com.defect.tracker.service.ReleaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    private ReleaseRepository releaseRepository;
    @Override
    public void saveRelease(ReleaseRequest releaseRequest) {
        Release release = new Release();
        BeanUtils.copyProperties(releaseRequest, release);
        releaseRepository.save(release);
    }

    @Override
    public List<ReleaseResponse> getAllRelease() {
        List<ReleaseResponse> releaseResponses= new ArrayList<>();
        List<Release> releases = releaseRepository.findAll();
        for (Release release : releases) {
            ReleaseResponse releaseResponse = new ReleaseResponse();
            BeanUtils.copyProperties(release, releaseResponse);
            releaseResponses.add(releaseResponse);
        }
        return releaseResponses;
    }

    @Override
    public boolean isReleaseExists(String name) {
        return releaseRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public ReleaseResponse getReleaseById(Long id) {
        Release release = releaseRepository.findById(id).get();
       ReleaseResponse releaseResponse= new ReleaseResponse();
        BeanUtils.copyProperties(release, releaseResponse);
        return releaseResponse;
    }

    @Override
    public boolean existByRelease(Long id) {
        return releaseRepository.existsById(id);
    }

    @Override
    public boolean isUpdatedReleaseNameExist(Long id, String name) {
        return releaseRepository.existsByNameIgnoreCaseAndIdNot(name,id);
    }

    @Override
    public void deleteRelease(Long id) {
        releaseRepository.deleteById(id);

    }
}
