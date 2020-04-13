package com.employee.management.controller;

import java.util.List;
import javax.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.employee.management.domain.Employee;
import com.employee.management.domain.Response;
import com.employee.management.properties.EmployeeProperties;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Controller for Employee Resource. It contains end points to process requests for employee
 * resources in JSON or XML formal
 * 
 * @author umar.tahir
 * 
 */

@RequestMapping(value = "/employees")
public interface EmployeeController {


  /**
   * This end point is used to retrieve employee resource present against its name and can be access
   * by any user with roles of admin or viewer
   * 
   * @param name
   * @return Employee
   */

  @GetMapping(value = "/{name}")
  @Secured({"ROLE_ADMIN", "ROLE_VIEWER"})
  @ApiOperation(value = EmployeeProperties.GET_BY_NAME, notes = EmployeeProperties.GET_BY_NAME_NOTE,
      response = Employee.class)

  ResponseEntity<List<Employee>> getEmployeeByName(
      @ApiParam(value = EmployeeProperties.GET_BY_NAME_API_VALUE) @PathVariable String name);

  /**
   * 
   * This end point is used to retrieve list of all employee resources and is used by users who have
   * roles of Admin or viewer
   * 
   * @return List<Employee>
   */

  @GetMapping(value = "")
  @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_ADMIN')")
  @ApiOperation(value = EmployeeProperties.GET_ALL_DETAILS,
      notes = EmployeeProperties.GET_ALL_DETAILS_NOTE, response = Employee.class)

  ResponseEntity<List<Employee>> getAllEmployees();


  /**
   * This end point is used to save employee resource in data store and used by all the users
   * 
   * @param Employee
   * @return Resource that is saved in data source with response description
   */

  @PostMapping(value = "")
  @PermitAll
  @ApiOperation(value = EmployeeProperties.SAVE_EMPLOYEE,
      notes = EmployeeProperties.SAVE_EMPLOYEE_DETAIL, response = Response.class)

  ResponseEntity<Response> saveEmployee(
      @ApiParam(value = EmployeeProperties.SAVE_EMPLOYEE_API_VALUE,
          required = true) @RequestBody Employee employee);


  /**
   * This end point is used to update employee resource in data store if it is present and this end
   * point is used by user with admin role
   * 
   * @param Employee
   * @return Resource that is updated in data source with response description
   */

  @PutMapping(value = "")
  @Secured("ROLE_ADMIN")
  @ApiOperation(value = EmployeeProperties.FIND_EMPLOYEE_BY_ID_IN_EMPLOYEE_REQUEST,
      notes = EmployeeProperties.PROVIDED_UPDATED_DETAILS, response = Response.class)

  ResponseEntity<Response> updateEmployee(
      @ApiParam(value = EmployeeProperties.EMPLOYEE_VALUE_FOR_UPDATE,
          required = true) @RequestBody Employee employee);


  /**
   * This end point remove employee resource identified by Id and is used by user with admin role
   * 
   * @param id
   * @return Response with Resource removed details
   * 
   */

  @DeleteMapping(value = "/{id}")
  @Secured("ROLE_ADMIN")
  @ApiOperation(value = EmployeeProperties.REMOVE_EMPLOYEE,
      notes = EmployeeProperties.REMOVE_EMPLOYEE_DETAILS, response = Response.class)
  ResponseEntity<Response> deleteEmployee(@ApiParam(
      value = EmployeeProperties.REMOVE_EMPLOYEE_DETAILS_ID, required = true) @PathVariable int id);

  /**
   * This end point is added to practice @Transactional and 2nd level cache. It is not following all
   * best coding standards done same as on other end points. Also its test case is not added.
   * 
   * @param employee
   * @return
   */

  @PostMapping(value = "/savelist")
  @PermitAll
  ResponseEntity<String> saveEmployeeList(@RequestBody List<Employee> employee);


}
