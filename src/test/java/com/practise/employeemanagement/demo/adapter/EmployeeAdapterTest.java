package com.practise.employeemanagement.demo.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import com.employee.management.adapter.EmployeeAdapter;
import com.employee.management.domain.Employee;
import com.employee.management.model.EmployeeEntity;

/**
 * These test cases corresponds to EmployeeAdapter Class
 * 
 * @author umar.tahir
 *
 */
public class EmployeeAdapterTest {

  EmployeeAdapter adapter = new EmployeeAdapter();

  /**
   * This method is used to setup Employee Entity
   * 
   * @return Employee Entity
   */
  private EmployeeEntity setUpEmployeeEntity() {
    return new EmployeeEntity().address("House no 34 R PGECHS").experienceInYears(2).employeeId(1)
        .name("Umar Tahir").qualifications("BS(CS").salary(20000);
  }

  /**
   * This method is used to setup Employee Dto
   * 
   * @return Employee Dto
   */
  private Employee setUpEmployeeDto() {
    return Employee.builder().address("House no 34 R PGECHS").experienceInYears(2).id(1)
        .name("Umar Tahir").qualifications("BS(CS").salary(20000).build();
  }

  /**
   * Tests to check adaptDtoToEntity method
   */
  @Test
  public void adaptDtoToEntityTest() {
    assertEquals(setUpEmployeeEntity(), adapter.adaptDtoToEntity(setUpEmployeeDto()));
  }

  /**
   * Tests to check adaptEntityToDto method
   */
  @Test
  public void adaptEntityToDtoTest() {
    assertEquals(setUpEmployeeDto(), adapter.convertToDto(setUpEmployeeEntity()));
  }

}
