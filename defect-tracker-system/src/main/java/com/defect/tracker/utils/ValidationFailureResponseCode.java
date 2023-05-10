package com.defect.tracker.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:MessagesAndCodes.properties")
@Getter
@Setter
public class ValidationFailureResponseCode {
  // Common Success code
  @Value("${code.success.common}")
  private String commonSuccessCode;

  @Value("${code.failure.common}")
  private String failureCode;

  // Validation code for Designation
  @Value("${code.validation.designation.alreadyExists}")
  private String designationAlreadyExists;

  @Value("${code.validation.designation.notExists}")
  private String designationNotExistsCode;

  // Messages for Designation
  @Value("${message.success.save.designation}")
  private String saveDesignationSuccessMessage;

  @Value("${message.validation.designation.alreadyExists}")
  private String validationDesignationAlreadyExists;

  @Value("${message.success.getAll.designation}")
  private String getAllDesignationSuccessMessage;

  @Value("${message.validation.designation.notExists}")
  private String designationNotExistsMessage;

  @Value("${message.success.getById.designation}")
  private String getDesignationByIdSuccessMessage;

  @Value("${message.success.update.designation}")
  private String updateDesignationSuccessMessage;

  @Value("${message.success.delete.designation}")
  private String deleteDesignationSuccessMessage;


  //validation code for Priority
  @Value("${code.validation.priority.alreadyExists}")
  private String priorityAlreadyExists;
  @Value("${code.validation.priority.notExists}")
  private String priorityNotExistsCode;

  // Messages for Priority
  @Value("${message.validation.priority.alreadyExists}")
  private String validationPriorityAlreadyExists;

  @Value("${message.success.save.priority}")
  private String savePrioritySuccessMessage;

  @Value("${message.success.getAll.priority}")
  private String getAllPrioritySuccessMessage;

  @Value("${message.validation.priority.notExists}")
  private String priorityNotExistsMessage;

  @Value("${message.success.getById.priority}")
  private String getPriorityByIdSuccessMessage;

  @Value("${message.success.deleteById.priority}")
  private String deletePrioritySuccessMessage;

  @Value("${message.success.update.priority}")
  private String updatePrioritySuccessMessage;

  //validation code for Priority
  @Value("${code.validation.seviarity.alreadyExists}")
  private String seviarityAlreadyExists;
  @Value("${code.validation.seviarity.notExists}")
  private String seviarityNotExistsCode;

  // Messages for seviarity
  @Value("${message.validation.seviarity.alreadyExists}")
  private String validationSeviarityAlreadyExists;

  @Value("${message.success.save.seviarity}")
  private String saveSeviaritySuccessMessage;

  @Value("${message.success.getAll.seviarity}")
  private String getAllSeviaritySuccessMessage;

  @Value("${message.validation.seviarity.notExists}")
  private String validationSeviarityNotExists;

  @Value("@{message.success.getById.seviarity}")
  private String getSeviaritySuccessMessage;

  @Value("${message.success.deleteById.seviarity}")
  private String deleteSeviaritySuccessMessage;

  @Value("${message.success.update.seviarity}")
  private String updateSeviaritySuccessMessage;

  //validation code for DefectType
  @Value("${code.validation.defectType.alreadyExists}")
  private String defectTypeAllreadyExists;
  @Value("${code.validation.defectType.notExists}")
  private String defectTypeNotExists;

  // message for defectType
  @Value("${message.validation.defectType.alreadyExists}")
  private String valdiationDefectTypeAllReadyExists;
  @Value("${message.validation.defectType.notExists}")
  private String validationDefcetTypenotExists;
  @Value("${message.success.save.defectType}")
  private String saveDefectTypeSuccessMessage;

  @Value("${message.success.update.defectType}")
  private String updateDefectTypeSuccessMessage;

  @Value("${message.success.deleteById.defectType}")
  private String deleteDefectTypeSuccessMessage;

  @Value("${message.success.getAll.defectType}")
  private String getAllDefectTypeSuccessMessage;

  @Value("${message.success.getById.defectType}")
  private String getDefectTypeSuccessMessage;

  //validation code for DefectStatus
  @Value("${code.validation.defectStatus.alreadyExists}")
  private String defectStatusAllreadyExists;
  @Value("${code.validation.defectStatus.notExists}")
  private String defectStatusNotExists;

  // message for defectType
  @Value("${message.validation.defectStatus.alreadyExists}")
  private String valdiationDefectStatusAllReadyExists;
  @Value("${message.validation.defectstatus.notExists}")
  private String validationDefcetStatusnotExists;
  @Value("${message.success.save.defectStatus}")
  private String saveDefectStatusSuccessMessage;
  @Value("${message.success.update.defectStatus}")
  private String updateDefectStatusSuccessMessage;
  @Value("${message.success.deleteById.defectStatus}")
  private String deleteDefectStatusSuccessMessage;
  @Value("${message.success.getAll.defectStatus}")
  private String getAllDefectStatusSuccessMessage;
  @Value("${message.success.getById.defectStatus}")
  private String getDefectStatusSuccessMessage;

  //validation code for ProjectStatus
  @Value("${code.validation.projectStatus.alreadyExists}")
  private String projectStatusAllreadyExists;
  @Value("${code.validation.projectStatus.notExists}")
  private String projectStatusNotExists;

  // message for ProjectStatus
  @Value("${message.validation.projectStatus.alreadyExists}")
  private String valdiationProjectStatusAllReadyExists;
  @Value("${message.validation.projectStatus.notExists}")
  private String validationProjectStatusnotExists;
  @Value("${message.success.save.projectStatus}")
  private String saveProjectStatusSuccessMessage;
  @Value("${message.success.update.projectStatus}")
  private String updateProjectStatusSuccessMessage;
  @Value("${message.success.deleteById.projectStatus}")
  private String deleteProjectStatusSuccessMessage;
  @Value("${message.success.getAll.projectStatus}")
  private String getAllProjectStatusSuccessMessage;
  @Value("${message.success.getById.projectStatus}")
  private String getProjectStatusSuccessMessage;
}
