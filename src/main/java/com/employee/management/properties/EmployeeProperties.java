package com.employee.management.properties;

import lombok.experimental.UtilityClass;

/**
 * Constant class for Swagger Properties of Employee API
 * 
 * @author umar.tahir
 *
 */

@UtilityClass
public class EmployeeProperties {

  public final String GET_BY_NAME = "Find Employee By Name";
  public final String GET_BY_NAME_NOTE =
      "Provide a name to look up for Employee from Employees list. Only users with roles ROLE_ADMIN, ROLE_VIEWER can access this API";
  public final String GET_BY_NAME_API_VALUE =
      "Name value for a Employee which you need to retrieve";

  public final String GET_ALL_DETAILS = "Get All Employees Details";
  public final String GET_ALL_DETAILS_NOTE =
      "Get List of all Employees Exist in System. Only users with roles ROLE_ADMIN, ROLE_VIEWER can access this API";

  public final String SAVE_EMPLOYEE = "Store Employee resource";
  public final String SAVE_EMPLOYEE_DETAIL =
      "Provide Employee details. All the details of the Employees will be stored in the System";
  public final String SAVE_EMPLOYEE_API_VALUE =
      "Employee value which you need to store in the System";

  public final String FIND_EMPLOYEE_BY_ID_IN_EMPLOYEE_REQUEST =
      "Find Employee By id Present in Employe Object";
  public final String PROVIDED_UPDATED_DETAILS =
      "Provide Employee details. All the details of the Employees will be updated in the System";
  public final String EMPLOYEE_VALUE_FOR_UPDATE =
      "Employee value which you need to update in the System";

  public final String REMOVE_EMPLOYEE = "Remove Employee Details from System";
  public final String REMOVE_EMPLOYEE_DETAILS =
      "Provide Employee id. All the details of the Employees will be removed from the System";

  public final String REMOVE_EMPLOYEE_DETAILS_ID =
      "Id value for an Employee who you want to remove from system";

  public final String GET_NBS_CDK_EMPLOYEES = "Get Employees of CDK and NBS Companies";

  public final String GET_NBS_CDK_EMPLOYEES_DETAILS =
      "This will get Employees From CDK service as well from NorthBay Company as well";

  public final String EMPLOYEE_DETAILS_API = "Employee Details Api";
  public final String EMPLOYEE_DETAILS_API_Description =
      "Sample Api for Employee developed by Umar Tahir Overlooked by Noreen Sharif";
  public final String EMPLOYEE_DETAILS_API_VERSION = "1.0";
  public final String EMPLOYEE_DETAILS_API_ALLLOWED_TO_USE =
      "Only Northbay/CDK Employees can use this";
  public final String EMPLOYEE_DETAILS_DEVELOPER = "Umar Tahir";
  public final String EMPLOYEE_DETAILS_WEBSITE = "www.northbaysoultions.com";
  public final String EMPLOYEE_DETAILS_EMAIL = "umartahir@live.com";
  public final String EMPLOYEE_DETAILS_API_LISENCE = "Api Lisence";

  public final String SERVICE_PROXY_NBS_CDK_SERVICE = "AllowNBSCDK";
  public final String SERVICE_PROXY_CDK_SERVICE = "AllowCDK";
  public final String SERVICE_PROXY_NBS_SERVICE = "AllowNBS";


}
