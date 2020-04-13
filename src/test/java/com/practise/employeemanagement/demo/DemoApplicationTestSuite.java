package com.practise.employeemanagement.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.practise.employeemanagement.demo.adapter.EmployeeAdapterTest;
import com.practise.employeemanagement.demo.controller.impl.EmployeeControllerTest;
import com.practise.employeemanagement.demo.employee.service.impl.CdkEmployeeServiceTest;
import com.practise.employeemanagement.demo.employee.service.impl.NorthBayEmployeeServiceTest;
import com.practise.employeemanagement.demo.exception.handler.EmployeeExceptionHandlerTest;
import com.practise.employeemanagement.demo.repository.EmployeeRepoTest;
import com.practise.employeemanagement.demo.validator.EmployeeValidatorTest;

/**
 * Test Suite for Employee Test Cases
 * 
 * @author umar.tahir
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({EmployeeAdapterTest.class, EmployeeControllerTest.class,
    EmployeeExceptionHandlerTest.class, EmployeeValidatorTest.class,
    NorthBayEmployeeServiceTest.class, CdkEmployeeServiceTest.class, EmployeeRepoTest.class})
public class DemoApplicationTestSuite {

}
