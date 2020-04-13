package com.practise.employeemanagement.demo.employee.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.employee.management.adapter.EmployeeAdapter;
import com.employee.management.domain.Employee;
import com.employee.management.exceptions.EmployeeException;
import com.employee.management.model.EmployeeEntity;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.service.impl.CdkEmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class CdkEmployeeServiceTest {

  final static int firstEmployeeId = 1;
  final static int secondEmployeeId = 2;


  @InjectMocks
  CdkEmployeeService service;
  @Mock
  EmployeeAdapter adapter;


  @Mock
  EmployeeRepository repository;

  @Before
  public void setUp() {

    when(adapter.convertToDto(any())).thenReturn(initEmployeeDto(firstEmployeeId))
        .thenReturn(initEmployeeDto(secondEmployeeId));

    when(repository.findById(anyInt()))
        .thenReturn(Optional.of(initEmployeeEntity(firstEmployeeId)));
  }

  private EmployeeEntity initEmployeeEntity(int employeeID) {
    return new EmployeeEntity().address("House no 34 R PGECHS").experienceInYears(2)
        .employeeId(employeeID).name("Umar Tahir").qualifications("BS(CS)").salary(200000)
        .companyName("Northbay Solutions");

  }

  private Employee initEmployeeDto(int employeeId) {
    return new Employee(employeeId, "Umar Tahir", "House no 34 R PGECHS", "Ph.D", 200000, 2,
        "Northbay Solutions");
  }

  private Employee initEmptyEmployeeDto(int employeeId) {
    return new Employee(employeeId, "", "", "", 200000, 2, "Northbay Solutions");
  }

  @Test
  public void testSaveEmployee_WhenEmployeeAddressNameAndSalaryIsNotEmpty() {
    when(repository.save(any())).thenReturn(initEmployeeEntity(firstEmployeeId));
    assertEquals(initEmployeeDto(firstEmployeeId),
        service.saveEmployee(initEmployeeDto(firstEmployeeId)));

  }

  @Test(expected = EmployeeException.class)
  public void testSaveEmployee_WhenEmployeeAddressNameAndSalaryIsEmpty() {
    service.saveEmployee(initEmptyEmployeeDto(firstEmployeeId));
  }

  @Test
  public void testDeleteEmployee() {
    ArgumentCaptor<EmployeeEntity> argumentCaptor = ArgumentCaptor.forClass(EmployeeEntity.class);
    service.deleteEmployee(firstEmployeeId);

    verify(repository).delete(any());
    verify(repository).delete((argumentCaptor.capture()));
    assertEquals(initEmployeeEntity(1), argumentCaptor.getValue());

  }

  @Test
  public void testUpdateEmployee() {
    when(repository.save(any())).thenReturn(initEmployeeEntity(firstEmployeeId));
    assertEquals(initEmployeeDto(firstEmployeeId),
        service.updateEmployee(initEmployeeDto(firstEmployeeId)));

  }

  // @Test
  // public void testGetByEmployeeId_WhenIdIsPositiveNumber() {
  // assertEquals(initEmployeeDto(firstEmployeeId), service.getByEmployeeName(firstEmployeeId));
  // }
  //
  // @Test(expected = EmployeeException.class)
  // public void testGetByEmployeeId_WhenIdIsNonPositiveInteger() {
  // assertEquals(initEmployeeDto(firstEmployeeId), service.getByEmployeeName(0));
  // }

  @Test
  public void testGetAllEmployees_WhenListEmpty() {
    List<EmployeeEntity> getAllEmployee = new ArrayList<>();
    when(repository.findAll()).thenReturn(getAllEmployee);
    assertEquals(new ArrayList<Employee>(), service.getAllEmployee());
  }


  @Test
  public void testGetAllEmployees_WhenListContainEmployees() {
    List<EmployeeEntity> getAllEmployee = new ArrayList<>();
    getAllEmployee.add(initEmployeeEntity(1));
    getAllEmployee.add(initEmployeeEntity(2));
    when(repository.findAll()).thenReturn(getAllEmployee);

    List<Employee> getAllEmployeeDto = new ArrayList<>();
    getAllEmployeeDto.add(initEmployeeDto(1));
    getAllEmployeeDto.add(initEmployeeDto(2));

    assertEquals(getAllEmployeeDto, service.getAllEmployee());
  }


}
