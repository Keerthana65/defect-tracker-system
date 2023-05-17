package com.defect.tracker.utils;

public class EndpointURI {
  private static final String BASE_API_PATH = "/api/v1/";
  private static final String ID = "/{id}";

  private static final String FEILD = "/{feild}";
  private static final String pageSize="/{pageSize}";

  private static final String pageNumber="/{pageNumber}";

  private static final String sortProperty="/{sortProperty}";

  // URLs for Designation
  public static final String DESIGNATION = BASE_API_PATH + "designation";
  public static final String DESIGNATION_BY_ID = DESIGNATION + ID;
  public static final String SEARCH_AND_PAGINATION_DESIGNATION=DESIGNATION+"/search";

  public static final String PRIORITY = BASE_API_PATH + "priority";
  public static final String PRIORITY_BY_ID = PRIORITY + ID;
  public static final String PRIORITYPAGINATION=PRIORITY+"/search";
  public static final String PRIORITYSEARCH= BASE_API_PATH + "priority"+FEILD;
  public static final String DEFECT = BASE_API_PATH + "defect";
  public static final String DEFECT_BY_ID = DEFECT + ID;
  public static final String SEARCH_AND_PAGINATION_DEFECT=DEFECT+"/search";
  public static final String SEVIARITY = BASE_API_PATH + "seviarity";
  public static final String SEVIARITY_BY_ID = SEVIARITY + ID;
  public static final String SEARCH_AND_PAGINATION_SEVIARITY=SEVIARITY+"/search";

  public static final String DEFECTTYPE = BASE_API_PATH + "defectType";
  public static final String DEFECTTYPE_BY_ID = DEFECTTYPE + ID;

  public static final String SEARCH_AND_PAGINATION_DEFECTTYPE = DEFECTTYPE+"/search";

  public static final String DEFECTSTATUS = BASE_API_PATH + "defectStatus";
  public static final String DEFECTSTATUS_BY_ID = DEFECTSTATUS + ID;

  public static final String SEARCH_AND_PAGINATION_DEFECTSTATUS = DEFECTSTATUS+"/search";

  public static final String PROJECTSTATUS = BASE_API_PATH + "projectstatus";
  public static final String PROJECTSTATUS_BY_ID = PROJECTSTATUS + ID;

  public static final String RELEASE = BASE_API_PATH + "release";
  public static final String RELEASE_BY_ID = RELEASE + ID;
  public static final String SEARCH_AND_PAGINATION_RELEASE=RELEASE+"/search";

  public static final String ROLE= BASE_API_PATH + "role";
  public static final String ROLE_BY_ID = ROLE + ID;
  public static final String SEARCH_AND_PAGINATION_ROLE=ROLE+"/search";

  public static final String EMPLOYEE= BASE_API_PATH + "employee";
  public static final String EMPLOYEE_BY_ID = EMPLOYEE + ID;
  public static final String SEARCH_AND_PAGINATION_EMPLOYEE = EMPLOYEE+"/search";

  public static final String PROJECT= BASE_API_PATH + "project";
  public static final String PROJECT_BY_ID = PROJECT + ID;

  public static final String PROJECTALLOCATION= BASE_API_PATH + "projectallocation";
  public static final String PROJECTALLOCATION_BY_ID = PROJECTALLOCATION + ID;

  public static final String paginationAndSorting=BASE_API_PATH+pageNumber+pageSize+sortProperty;


}
