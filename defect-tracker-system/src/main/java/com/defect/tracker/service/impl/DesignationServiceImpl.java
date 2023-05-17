package com.defect.tracker.service.impl;

import com.defect.tracker.common.response.PaginatedContentResponse;
import com.defect.tracker.entities.Designation;
import com.defect.tracker.entities.QDesignation;
import com.defect.tracker.repositories.DesignationRepository;
import com.defect.tracker.response.dto.DesignationResponse;
import com.defect.tracker.resquest.dto.DesignationRequest;
import com.defect.tracker.search.dto.DesiginationSearch;
import com.defect.tracker.service.DesignationService;
import com.defect.tracker.utils.Utils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DesignationServiceImpl implements DesignationService {
  @Autowired
  private DesignationRepository designationRepository;

  @Transactional
  public void saveDesignation(DesignationRequest designationRequest) {
    Designation designation = new Designation();
    BeanUtils.copyProperties(designationRequest, designation);
    designationRepository.save(designation);
  }

  @Transactional
  public List<DesignationResponse> getAllDesignation() {
    List<DesignationResponse> designationResponses = new ArrayList<>();
    List<Designation> designations = designationRepository.findAll();
    for (Designation designation : designations) {
      DesignationResponse designationResponse = new DesignationResponse();
      BeanUtils.copyProperties(designation, designationResponse);
      designationResponses.add(designationResponse);
    }
    return designationResponses;
  }

  @Override
  public boolean isDesignationExists(String name) {
    return designationRepository.existsByNameIgnoreCase(name);
  }

  @Transactional
  public DesignationResponse getDesignationById(Long id) {
    Designation designation = designationRepository.findById(id).get();
    DesignationResponse designationResponse = new DesignationResponse();
    BeanUtils.copyProperties(designation, designationResponse);
    return designationResponse;
  }

  @Override
  public boolean existByDesignation(Long id) {
    return designationRepository.existsById(id);
  }

  @Override
  public boolean isUpdatedDesignationNameExist(Long id, String name) {
    return designationRepository.existsByNameIgnoreCaseAndIdNot(name, id);
  }

  @Override
  public void deleteDesignation(Long id) {
    designationRepository.deleteById(id);
  }


  @Override
  public List<DesignationResponse> multiSearchDesignationSearch(Pageable pageable, PaginatedContentResponse.Pagination pagination, DesiginationSearch desiginationSearch) {
    BooleanBuilder booleanBuilder=new BooleanBuilder();
    if(Utils.isNotNullAndEmpty(desiginationSearch.getDesignationName()))
    {
      booleanBuilder.and(QDesignation.designation.name.eq(desiginationSearch.getDesignationName()));
    }
    List<DesignationResponse> designationResponseList=new ArrayList<>();
    Page<Designation> designationPage=designationRepository.findAll(booleanBuilder,pageable);
    pagination.setTotalRecords(designationPage.getTotalElements());
    pagination.setTotalPages(designationPage.getTotalPages());
    for (Designation designation:designationPage
    ) {
      DesignationResponse designationResponse=new DesignationResponse();
      BeanUtils.copyProperties(designation,designationResponse);
      designationResponseList.add(designationResponse);
    }
    return designationResponseList;
  }
}
