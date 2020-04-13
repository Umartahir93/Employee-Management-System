package com.employee.management.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.employee.management.domain.Employee;
import com.employee.management.exceptions.EmployeeException;

/**
 * The business layer which perform functions on employee resources
 * 
 * @author umar.tahir
 *
 */

public interface EmployeeService {
  /**
   * It returns employee resource according to id from data layer
   * 
   * @param name
   * @return Employee Object
   * @throws EmployeeException
   */
  List<Employee> getByEmployeeName(String name);

  /**
   * it returns all the employee resources stored in database
   * 
   * @return all employee resources from data source
   * @throws EmployeeException
   */
  List<Employee> getAllEmployee();

  /**
   * This stores employee in the data source
   * 
   * @param employee
   * @return employee
   */
  Employee saveEmployee(Employee employee);

  /**
   * This returns updated employee if employee is present otherwise return exception if employee is
   * not present
   * 
   * @param employee
   * @return employee
   * @throws EmployeeException
   */
  Employee updateEmployee(Employee employee);


  /**
   * This method delete the employee resource
   * 
   * @param id
   * @throws EmployeeException
   */
  void deleteEmployee(int id);

  /**
   * This stores employees in the data source
   * 
   * @param List<employee>
   * @throws DataAccessException
   * 
   */
  void saveEmployeeList(List<Employee> employee);


}
