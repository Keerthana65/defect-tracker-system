package com.defect.tracker.utils;

public class EndpointURI {
  private static final String BASE_API_PATH = "/api/v1/";
  private static final String ID = "/{id}";

  // URLs for Designation
  public static final String DESIGNATION = BASE_API_PATH + "designation";
  public static final String DESIGNATION_BY_ID = DESIGNATION + ID;

  public static final String PRIORITY = BASE_API_PATH + "priority";
  public static final String PRIORITY_BY_ID = PRIORITY + ID;

  public static final String SEVIARITY = BASE_API_PATH + "seviarity";
  public static final String SEVIARITY_BY_ID = SEVIARITY + ID;

  public static final String DEFECTTYPE = BASE_API_PATH + "defectType";
  public static final String DEFECTTYPE_BY_ID = DEFECTTYPE + ID;

  public static final String DEFECTSTATUS = BASE_API_PATH + "defectStatus";
  public static final String DEFECTSTATUS_BY_ID = DEFECTSTATUS + ID;

  public static final String PROJECTSTATUS = BASE_API_PATH + "projectstatus";
  public static final String PROJECTSTATUS_BY_ID = PROJECTSTATUS + ID;

  public static final String RELEASE = BASE_API_PATH + "release";
  public static final String RELEASE_BY_ID = RELEASE + ID;

  public static final String ROLE= BASE_API_PATH + "role";
  public static final String ROLE_BY_ID = ROLE + ID;

  public static final String EMPLOYEE= BASE_API_PATH + "employee";
  public static final String EMPLOYEE_BY_ID = EMPLOYEE + ID;

  public static final String PROJECT= BASE_API_PATH + "project";
  public static final String PROJECT_BY_ID = PROJECT + ID;

  public static final String PROJECTALLOCATION= BASE_API_PATH + "projectallocation";
  public static final String PROJECTALLOCATION_BY_ID = PROJECTALLOCATION + ID;


}
