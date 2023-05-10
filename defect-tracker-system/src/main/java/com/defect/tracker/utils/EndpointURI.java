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

}
