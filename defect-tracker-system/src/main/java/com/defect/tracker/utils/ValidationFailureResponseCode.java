package com.defect.tracker.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

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
  @Value("${code.validation.priority.notExists}")
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
  @Value("@{message.success.getById.Seviarity}")
  private String getSeviaritySuccessMessage;

  @Value("${message.success.deleteById.Seviarity}")
  private String deleteSeviaritySuccessMessage;

}
