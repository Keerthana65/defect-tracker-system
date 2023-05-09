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
}
