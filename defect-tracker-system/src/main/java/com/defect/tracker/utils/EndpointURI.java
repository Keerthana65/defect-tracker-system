package com.defect.tracker.utils;

public class EndpointURI {
  private static final String BASE_API_PATH = "/api/v1/";
  private static final String ID = "/{id}";

  // URLs for Designation
  public static final String DESIGNATION = BASE_API_PATH + "designation";
  public static final String DESIGNATION_BY_ID = DESIGNATION + ID;

  public static final String PRIORITY = BASE_API_PATH + "priority";

  public static final String PRIORITY_BY_ID = PRIORITY + ID;

}
