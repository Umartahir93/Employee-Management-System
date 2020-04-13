package com.employee.management.utilities;

import java.util.function.IntPredicate;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import com.employee.management.domain.Employee;
import lombok.experimental.UtilityClass;

/**
 * Service Validator class for Employee Resource
 * 
 * @author umar.tahir
 *
 */
@UtilityClass
public class EmployeeServiceValidator {

  /**
   * This checks that employee address,name and salary is not empty
   * 
   * @return
   */
  public Predicate<Employee> isEmployeeAddressNameAndSalaryEmpty() {
    return employee -> (StringUtils.isEmpty(employee.getAddress())
        || StringUtils.isEmpty(employee.getName())) || (employee.getSalary() == 0);
  }

  /**
   * This lambda Expression checks employee id should be greater than zero
   * 
   * @return
   */

  public Predicate<String> employeeNameEmptyCheck() {
    return name -> StringUtils.isEmpty(name);
  }

  public IntPredicate employeeIdGreaterThanZero() {
    return id -> id > 0;
  }


}
