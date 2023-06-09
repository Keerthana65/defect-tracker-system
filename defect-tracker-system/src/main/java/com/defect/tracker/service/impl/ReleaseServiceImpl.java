package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Release;
import com.defect.tracker.entities.QRelease;
import com.defect.tracker.repositories.ReleaseRepository;
import com.defect.tracker.response.dto.ReleaseResponse;
import com.defect.tracker.resquest.dto.ReleaseRequest;
import com.defect.tracker.search.dto.ReleaseSearch;
import com.defect.tracker.service.ReleaseService;
import com.defect.tracker.utils.Utils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<ReleaseResponse> multiSearchRealese(Pageable pageable, PaginatedContentResponse.Pagination pagination, ReleaseSearch releaseSearch) {
        BooleanBuilder booleanBuilder=new BooleanBuilder();
        if(Utils.isNotNullAndEmpty(releaseSearch.getReleaseName()))
        {
            booleanBuilder.and(QRelease.release.name.eq(releaseSearch.getReleaseName()));
        }
        List<ReleaseResponse> releaseResponseList=new ArrayList<>();
        Page<Release> releasePage=releaseRepository.findAll(booleanBuilder,pageable);
        pagination.setTotalRecords(releasePage.getTotalElements());
        pagination.setTotalPages(releasePage.getTotalPages());
        for (Release release:releasePage
        ) {
            ReleaseResponse releaseResponse=new ReleaseResponse();
            BeanUtils.copyProperties(release,releaseResponse);
            releaseResponseList.add(releaseResponse);
        }
        return releaseResponseList;
    }
}
