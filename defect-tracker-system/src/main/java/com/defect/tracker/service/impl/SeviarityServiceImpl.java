package com.defect.tracker.service.impl;

import com.defect.tracker.entities.Seviarity;
import com.defect.tracker.repositories.SeviarityRepository;
import com.defect.tracker.response.dto.SeviarityResponse;
import com.defect.tracker.resquest.dto.SeviarityRequest;
import com.defect.tracker.service.SeviarityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeviarityServiceImpl implements SeviarityService {

    @Autowired
    private SeviarityRepository seviarityRepository;
    @Override
    public void saveSeviarity(SeviarityRequest seviarityRequest) {
        Seviarity seviarity=new Seviarity();
        BeanUtils.copyProperties(seviarityRequest,seviarity);
        seviarityRepository.save(seviarity);
    }

    @Override
    public boolean isSeviarityExistsByName(String name) {
        return seviarityRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public boolean isSeviarityExistsByColor(String color) {
        return seviarityRepository.existsByColorIgnoreCase(color);
    }

    @Override
    public List<SeviarityResponse> getAllSeviarity()
    {
        List<SeviarityResponse> seviarityResponses=new ArrayList<>();
        List<Seviarity> seviarities=seviarityRepository.findAll();
        for (Seviarity seviarity:seviarities
             ) {
            SeviarityResponse seviarityResponse=new SeviarityResponse();
            BeanUtils.copyProperties(seviarity,seviarityResponse);
            seviarityResponses.add(seviarityResponse);
        }
        return seviarityResponses;
    }

    @Override
    public SeviarityResponse getSeviarityById(Long id) {
        SeviarityResponse seviarityResponse=new SeviarityResponse();
        Seviarity seviarity=seviarityRepository.findById(id).get();
        BeanUtils.copyProperties(seviarity,seviarityResponse);
        return seviarityResponse;
    }

    @Override
    public void deleteSeviarity(Long id)
    {
        seviarityRepository.deleteById(id);
    }

    @Override
    public boolean isUpdateSeviarityExistsByName(String name, Long id) {
        return seviarityRepository.existsByNameIgnoreCaseAndIdNot(name,id);
    }

    @Override
    public boolean isUpdateSeviarityExistsByColor(String color, Long id) {
        return seviarityRepository.existsByColorIgnoreCaseAndIdNot(color,id);
    }

    @Override
    public boolean existsByPriority(Long id) {
        return seviarityRepository.existsById(id);
    }
}
