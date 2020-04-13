package com.employee.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.employee.management.adapter.EmployeeAdapter;
import com.employee.management.domain.Employee;
import com.employee.management.exceptions.EmployeeException;
import com.employee.management.model.EmployeeEntity;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.service.EmployeeService;
import com.employee.management.specifications.EmployeeSpecification;
import com.employee.management.utilities.EmployeeServiceValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation class for Employee Service
 * 
 * @author umar.tahir
 *
 */

@Service("NorthBayEmployeeService")
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class NorthBayEmployeeService implements EmployeeService {

  EmployeeRepository repository;

  EmployeeAdapter adapter;

  /**
   * It returns employee resource according to id from data layer
   * 
   * @param name
   * @return Employee Object
   * @throws EmployeeException
   */

  @Override
  public List<Employee> getByEmployeeName(String name) {
    List<EmployeeEntity> employee;
    log.info("getByEmployeeId called in service with name {} ", name);

    if (EmployeeServiceValidator.employeeNameEmptyCheck().test(name)) {
      throw new EmployeeException(HttpStatus.BAD_REQUEST, "Empty Field", "Name cannot be empty");
    }

    employee = repository.findAll(EmployeeSpecification.getEmployeeByName(name));

    if (CollectionUtils.isEmpty(employee))
      throw new EmployeeException(HttpStatus.OK, "No Employee with this name Exist", "Not Found");

    return employee.stream().map(adapter::convertToDto).collect(Collectors.toList());

  }

  /**
   * This gets employee From Data Base
   * 
   * @param id
   * @return EmployeeEntity
   */

  private EmployeeEntity getEmployee(int id) {
    log.info("getEmployee called with id {} ro get employee resource", id);

    EmployeeEntity employee = null;

    if (EmployeeServiceValidator.employeeIdGreaterThanZero().test(id)) {
      log.info("Employee Respurce with id {} found", id);
      employee =
          repository.findById(id).orElseThrow(() -> new EmployeeException(HttpStatus.BAD_REQUEST,
              "Employee Id not Valid", "Employee with this Id doesnt Exist"));

    } else {
      log.info("Employee Respurce with id {} not found", id);
      throw new EmployeeException(HttpStatus.BAD_REQUEST, "Employee Id not Valid",
          "Only Positive Numbers Please!");
    }

    return employee;
  }

  /**
   * it returns all the employee resources stored in database
   * 
   * @return all employee resources from data source
   * @throws EmployeeException
   */

  @Override
  public List<Employee> getAllEmployee() {
    log.info("getAllEmployee in service layer is called");

    List<EmployeeEntity> employeeList = repository.findAll();

    log.info("employeeList {} for employee resource", employeeList.toString());

    return CollectionUtils.isEmpty(employeeList) ? new ArrayList<>()
        : employeeList.stream().map(adapter::convertToDto).collect(Collectors.toList());
  }

  /**
   * This stores employee in the data source
   * 
   * @param employee
   * @return employee
   */

  @Override
  public Employee saveEmployee(Employee employee) {
    log.info("saveEmployee in service layer is called with employee {}", employee.toString());

    if (!EmployeeServiceValidator.isEmployeeAddressNameAndSalaryEmpty().test(employee)) {
      log.info("employee {} pass all validations", employee.toString());

      return adapter.convertToDto(repository.save(adapter.adaptDtoToEntity(employee)));

    } else {
      log.info("employee {} does not pass all validations", employee.toString());

      throw new EmployeeException(HttpStatus.BAD_REQUEST, "Required Filed Missing",
          "Required Field Missing");
    }

  }

  /**
   * This returns updated employee if employee is present otherwise return exception if employee is
   * not present
   * 
   * @param employee
   * @return employee
   * @throws EmployeeException
   */

  @Override
  public Employee updateEmployee(Employee employee) {
    log.info("updateEmployee  with employee {} dtails is called", employee.toString());

    return adapter.convertToDto(repository.save(getEmployee(employee.getId())));

  }

  /**
   * This method delete the employee resource
   * 
   * @param id
   * @throws EmployeeException
   */

  @Override
  public void deleteEmployee(int id) {

    log.info("deleteEmployee with id {} is called", id);
    EmployeeEntity employee = getEmployee(id);
    log.info("Employee with id {} is found", id);
    repository.delete(employee);

  }

  /**
   * This method was developed as a practise for TDD
   * 
   * @param employee
   * @return
   */

  public Employee saveEmployeeInformation(Employee employee) {

    if (!EmployeeServiceValidator.isEmployeeAddressNameAndSalaryEmpty().test(employee)) {
      return adapter.convertToDto(repository.save(adapter.adaptDtoToEntity(employee)));
    } else {
      throw new EmployeeException(HttpStatus.BAD_REQUEST, "Required Filed Missing",
          "Required Field Missing");
    }

  }

  /**
   * This stores employees in the data source
   * 
   * @param List<employee>
   * @throws DataAccessException
   * 
   */

  @Override
  @Transactional
  public void saveEmployeeList(List<Employee> employee) {
    log.info("saveEmployeeList is called");

    if (CollectionUtils.isEmpty(employee)) {
      throw new EmployeeException(HttpStatus.BAD_REQUEST, "List is Empty",
          "No employee resource to save");
    }

    List<Employee> result = new ArrayList<>();

    for (EmployeeEntity employeeEntity : employee.stream().map(adapter::adaptDtoToEntity)
        .collect(Collectors.toList())) {
      result.add(adapter.convertToDto(repository.save(employeeEntity)));

    }

    log.info("Employee List is persisteted to the database");

  }

}
