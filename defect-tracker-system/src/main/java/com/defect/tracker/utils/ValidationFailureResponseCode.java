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

  @Value("${message.success.searchAndPagination.priority}")
  private String searchAndAPginationPRioritySuccessMessage;



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

  @Value("${message.success.searchAndPagination.seviarity}")
  private String searchAndPaginationSeviaritySuccessMessage;

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

  @Value("${message.success.searchAndPagination.defectType}")
  private String searchAndPaginationDefectTypeSuccessMessage;

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
  @Value("${message.success.searchAndPagination.defectStatus}")
  private String searchAndPaginationDefectStatusSuccessMessage;

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

  //validation code for ProjectStatus
  @Value("${code.validation.release.alreadyExists}")
  private String releaseAllreadyExists;
  @Value("${code.validation.release.notExists}")
  private String releaseNotExists;

  // message for ProjectStatus
  @Value("${message.validation.release.alreadyExists}")
  private String valdiationReleaseAllReadyExists;
  @Value("${message.validation.release.notExists}")
  private String validationReleasenotExists;
  @Value("${message.success.save.release}")
  private String saveReleaseSuccessMessage;
  @Value("${message.success.update.release}")
  private String updateReleaseSuccessMessage;
  @Value("${message.success.deleteById.release}")
  private String deleteReleaseSuccessMessage;
  @Value("${message.success.getAll.release}")
  private String getAllReleaseSuccessMessage;
  @Value("${message.success.getById.release}")
  private String getReleaseSuccessMessage;

  //validation code for ProjectStatus
  @Value("${code.validation.role.alreadyExists}")
  private String roleAllreadyExists;
  @Value("${code.validation.role.notExists}")
  private String roleNotExists;

  // message for ProjectStatus
  @Value("${message.validation.role.alreadyExists}")
  private String valdationRoleAllReadyExists;
  @Value("${message.validation.role.notExists}")
  private String validationRolenotExists;
  @Value("${message.success.save.role}")
  private String saveRoleSuccessMessage;
  @Value("${message.success.update.role}")
  private String updateRoleSuccessMessage;
  @Value("${message.success.deleteById.role}")
  private String deleteRoleSuccessMessage;
  @Value("${message.success.getAll.role}")
  private String getAllRoleSuccessMessage;
  @Value("${message.success.getById.role}")
  private String getRoleSuccessMessage;

  //validation code for EMployee
  @Value("${code.validation.employee.notExists}")
  private String employeeNotExists;
  @Value("${message.validation.email.alreadyExists}")
  private String employeeEmailAllReadyExists;
  @Value("${code.validation.phonenumber.alreadyExists}")
  private String employeePhoneNumberAllReadyExists;
  //Message for eMPLOYEE
  @Value("${message.validation.email.alreadyExists}")
  private String validationEmployeeEmailAllReadyExists;
  @Value("${message.validation.phonenumber.alreadyExists}")
  private String validationEmployeePhoneNumberAllReadyExists;
  @Value("${message.validation.employee.notExists}")
  private String validationEmployeeNotExists;
  @Value("${message.success.save.employee}")
  private String saveEmployeeSuccessMessage;
  @Value("${message.success.getAll.employee}")
  private String getAllEmployeeSUccessMessage;
  @Value("${message.success.getById.employee}")
  private String getEmployeeByIdSuccessMessage;
  @Value("${message.success.deleteById.employee}")
  private String deleteEMployeeSuccessMessage;
  @Value("${message.success.getPagination.Employee}")
  private String getEmployeePainationSuccessMessage;

  //validation code for PRoject
  @Value("${code.validation.name.allreadyexists}")
  private String projectNameAllReadyExists;
  @Value("${code.validation.project.allreadyexists}")
  private String projectAllreadyExists;
  @Value("${code.validation.project.notexists}")
  private String projectNotExists;
  //message for project
  @Value("${message.validation.name.allreadyexists}")
  private String projectNameAllreadyExistsMessage;
  @Value("${message.validation.project.allreadyexists}")
  private String projectAllreadyExistsMessage;
  @Value("${message.validation.project.notexists}")
  private String projectNotExistsMessage;
  @Value("${message.success.save.project}")
  private String saveProjectSuccessMessage;
  @Value("${message.success.update.project}")
  private String updateProjectSuccessMessage;
  @Value("${message.success.getAll.project}")
  private String getAllProjectSuccessMessage;
  @Value("${message.success.getById.project}")
  private String getProjectSuccessMessage;
  @Value("${message.success.delete.project}")
  private String deleteProjectSuccessMessage;
  //validation code for PRojectAllocation
  @Value("${code.validation.projectallocation.notexists}")
  private String projectallocationNotExists;
  //message for projectAllocation
  @Value("${message.success.save.projectallocation}")
  private String saveProjectAllocationSuccessMessage;
  @Value("${message.validation.projectallocation.notexists}")
  private String projectallocationNotExistsMessage;
  @Value("${message.success.update.projectallocation}")
  private String updateProjectAllocationSuccessMessage;
  @Value("${message.success.getAll.projectallocation}")
  private String getAllProjectAllocationSuccessMessage;
  @Value("${message.success.getById.projectallocation}")
  private String getProjectAllocationSuccessMessage;
  @Value("${message.success.delete.projectallocation}")
  private String deleteProjectAllocationSuccessMessage;
  //validationcode for Defect
  @Value("${code.validation.defect.nameALreadyexits}")
    private String nameAllReadyExists;
  @Value("${code.validation.defect.notExists}")
  private String defectNotExists;
  @Value("${code.validation.defect.ALreadyexits}")
  private String defectAllRedyExists;
  //validation message for defcet
  @Value("${message.validation.defect.nameALreadyexits}")
  private String nameAllRedyExistsMessage;
  @Value("${message.validation.defect.ALreadyexits}")
  private String defectAllRedyExistsMessage;
  @Value("${message.validation.defect.notExists}")
  private String defectNotExitsMessage;

  @Value("${message.success.save.defect}")
  private String saveDefectSuccessMessage;
  @Value("${message.success.update.defect}")
  private String updateDefectSucessMessage;
  @Value("${message.success.delete.defect}")
  private String deleteDefctSuccessMessage;
  @Value("${message.success.getALl.defect}")
  private String getAllDefectSuccessMessage;
  @Value("${message.success.getById.defect}")
  private String getDefctSuccessMessage;



}
