package com.employee.management.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.employee.management.domain.Employee;
import com.employee.management.model.EmployeeEntity;

/**
 * Employee adapter that converts Model Classes to Data Transfer Objects vice versa
 * 
 * @author umar.tahir
 *
 */

@Component
public class EmployeeAdapter {

  @Value("${employee.companyname}")
  private String companyName;

  /**
   * The method which Converts DTO to Entity
   * 
   * @param Employee Dto
   * @return Employee Entity
   */

  public EmployeeEntity adaptDtoToEntity(Employee employeeDto) {
    return new EmployeeEntity().address(employeeDto.getAddress())
        .experienceInYears(employeeDto.getExperienceInYears()).employeeId(employeeDto.getId())
        .name(employeeDto.getName()).qualifications(employeeDto.getQualifications())
        .salary(employeeDto.getSalary()).companyName(companyName);
  }

  /**
   * The method which converts Employee entity to DTO
   * 
   * @param Employee entity
   * @return Employee Dto
   */

  public Employee convertToDto(EmployeeEntity entity) {
    return Employee.builder().name(entity.name()).address(entity.address())
        .experienceInYears(entity.experienceInYears()).id(entity.employeeId())
        .qualifications(entity.qualifications()).salary(entity.salary())
        .companyName(entity.companyName()).build();
  }

}
