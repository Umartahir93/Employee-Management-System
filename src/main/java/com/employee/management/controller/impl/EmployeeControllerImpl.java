package com.employee.management.controller.impl;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.employee.management.controller.EmployeeController;
import com.employee.management.domain.Employee;
import com.employee.management.domain.Response;
import com.employee.management.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation class for Employee Controller. It contains all end points implementation to
 * process requests
 * 
 * @author umar.tahir
 *
 */

@Slf4j
@RestController
@AllArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {



  @Autowired
  EmployeeService service;


  /**
   * This end point is used to retrieve employee resource present against its name and can be access
   * by any user with roles of admin or viewer
   * 
   * @param name
   * @return Employee Resource
   */

  @Override
  public ResponseEntity<List<Employee>> getEmployeeByName(String name) {
    log.info("Get the resurce by {} Execution started ", name);
    return ResponseEntity.ok().body(service.getByEmployeeName(name));
  }

  /**
   * This end point is used to retrieve list of all employee resources and is used by users who have
   * roles of admin or viewer
   * 
   * @return Collection of All Employee Resources
   */

  @Override
  public ResponseEntity<List<Employee>> getAllEmployees() {
    log.info("Get the resurces Execution started ");
    return ResponseEntity.ok().body(service.getAllEmployee());
  }


  /**
   * This end point is used to save employee resource in data store and used by all the users
   * 
   * @param Employee
   * @return Resource that is saved in data source with response description
   */

  @Override
  public ResponseEntity<Response> saveEmployee(@Valid Employee employeeDto) {
    log.info("save the resurces Execution started with input {} ", employeeDto.toString());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new Response("Resource Created", "Created", service.saveEmployee(employeeDto)));
  }

  /**
   * This end point is used to update employee resource in data store if it is present and this end
   * point is used by user with admin role
   * 
   * @param Employee
   * @return Resource that is updated in data source with response description
   */

  @Override
  public ResponseEntity<Response> updateEmployee(Employee inputEmployeeDto) {
    log.info("update the resurces Execution started with input {}", inputEmployeeDto.toString());
    return ResponseEntity
        .ok(new Response("Resporce Updated", "Updated", service.updateEmployee(inputEmployeeDto)));
  }


  /**
   * This end point remove employee resource identified by Id and is used by user with admin role
   * 
   * @param id
   * @return Response with Resource removed details
   * 
   */

  @Override
  public ResponseEntity<Response> deleteEmployee(int id) {
    log.info("delete the resurces Execution started with id {}", id);
    service.deleteEmployee(id);
    return ResponseEntity
        .ok(new Response("Resource Removed", "Resource with id has been removed", null));
  }

  /**
   * This end point is added to practice @Transactional and 2nd level cache. It is not following
   * best coding standards as implemented on other end points. Also its test case is not added.
   * 
   * @param employee
   * @return
   */

  @Override
  public ResponseEntity<String> saveEmployeeList(List<Employee> employee) {

    log.info("save the resurces Execution started with input {} ", employee.toString());
    service.saveEmployeeList(employee);
    return ResponseEntity.status(HttpStatus.CREATED).body("Resources Created");
  }

}


