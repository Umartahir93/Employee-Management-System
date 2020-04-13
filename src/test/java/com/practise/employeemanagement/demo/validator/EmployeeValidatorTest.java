package com.practise.employeemanagement.demo.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import org.junit.Test;
import com.employee.management.domain.Employee;
import com.employee.management.utilities.EmployeeServiceValidator;

/**
 * Tests added to check validation Tests
 * 
 * @author umar.tahir
 *
 */
public class EmployeeValidatorTest {

  Employee employeeNameEmpty =
      new Employee(1, "", "House no 34 R PGECHS", "Ph.D", 10000, 10, "Nothbay Company");

  Employee employeeAddressEmpty =
      new Employee(1, "Umar Tahir ", "", "Ph.D", 10000, 10, "Nothbay Company");

  Employee employeeSalaryEmpty =
      new Employee(1, "Umar Tahir ", "", "Ph.D", 0, 10, "Nothbay Company");

  Employee employee = new Employee(1, "Umar Tahir", "House no", "Ph.D", 10, 10, "Nothbay Company");


  @Test
  public void isEmployeeNameSalaryAddressNotEmptyTest() {
    Predicate<Employee> isEmployeeAddressNameAndSalaryEmptyPredicate =
        EmployeeServiceValidator.isEmployeeAddressNameAndSalaryEmpty();
    assertFalse(isEmployeeAddressNameAndSalaryEmptyPredicate.test(employee));

  }

  @Test
  public void isEmployeeNameEmptyTest() {
    Predicate<Employee> isEmployeeAddressNameAndSalaryEmptyPredicate =
        EmployeeServiceValidator.isEmployeeAddressNameAndSalaryEmpty();
    assertTrue(isEmployeeAddressNameAndSalaryEmptyPredicate.test(employeeNameEmpty));

  }

  @Test
  public void isEmployeeAddressEmptyTest() {
    Predicate<Employee> isEmployeeAddressNameAndSalaryEmptyPredicate =
        EmployeeServiceValidator.isEmployeeAddressNameAndSalaryEmpty();
    assertTrue(isEmployeeAddressNameAndSalaryEmptyPredicate.test(employeeAddressEmpty));

  }

  @Test
  public void isEmployeeSalaryZeroTest() {
    Predicate<Employee> isEmployeeAddressNameAndSalaryEmptyPredicate =
        EmployeeServiceValidator.isEmployeeAddressNameAndSalaryEmpty();
    assertTrue(isEmployeeAddressNameAndSalaryEmptyPredicate.test(employeeSalaryEmpty));
  }

  @Test
  public void isEmployeeIdGreaterThenZero() {
    IntPredicate predicate = EmployeeServiceValidator.employeeIdGreaterThanZero();
    assertTrue(predicate.test(1));
  }

  @Test
  public void isEmployeeIdIsZero() {
    IntPredicate predicate = EmployeeServiceValidator.employeeIdGreaterThanZero();
    assertFalse(predicate.test(0));
  }

}
