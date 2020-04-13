package com.practise.employeemanagement.demo.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.employee.management.controller.impl.EmployeeControllerImpl;
import com.employee.management.domain.Employee;
import com.employee.management.domain.Response;
import com.employee.management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit Test cases for Employee Controller
 * 
 * @author umar.tahir
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeControllerImpl.class)
public class EmployeeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  EmployeeService service;

  RequestBuilder getRequest;
  Employee actualEmployeeValue;
  Employee expectedEmployeeValue;
  Employee firstEmployee;
  Employee secondEmployee;
  ArgumentCaptor<Employee> argumentCaptor;
  ObjectMapper objectToJsonConvertor = new ObjectMapper();

  List<Employee> expectedValueAllEmployeesList;
  List<Employee> actualValueAllEmployeesList;



  /**
   * Set up for mock ups
   */

  @Before
  public void beforeTest() {

    actualEmployeeValue =
        new Employee(1, "Ali", "House no 34 R PGECHS", "Ph.D", 10000, 10, "Nothbay Company");

    actualEmployeeValue =
        new Employee(1, "Ali", "House no 34 R PGECHS", "Ph.D", 10000, 10, "Nothbay Company");

    expectedEmployeeValue =
        new Employee(1, "Ali", "House no 34 R PGECHS", "Ph.D", 10000, 10, "Nothbay Company");

    firstEmployee =
        new Employee(1, "Ali", "House no 34 R PGECHS", "Ph.D", 10000, 10, "NorthBay Solutions");

    secondEmployee =
        new Employee(1, "Umar", "House no 34 R PGECHS", "Ph.D", 10000, 10, "NorthBay Solutions");


    expectedValueAllEmployeesList = new ArrayList<Employee>();
    expectedValueAllEmployeesList.add(firstEmployee);
    expectedValueAllEmployeesList.add(secondEmployee);

    actualValueAllEmployeesList = new ArrayList<Employee>();
    actualValueAllEmployeesList.add(firstEmployee);
    actualValueAllEmployeesList.add(secondEmployee);

    argumentCaptor = ArgumentCaptor.forClass(Employee.class);

    when(service.getByEmployeeName("umar")).thenReturn(actualValueAllEmployeesList);
    when(service.saveEmployee(actualEmployeeValue)).thenReturn(expectedEmployeeValue);
  }

  /**
   * Method is used to test getByName Method
   * 
   * @throws Exception
   */
  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  public void testGetByName() throws Exception {

    RequestBuilder getRequest = MockMvcRequestBuilders.get("/employees/umar")
        .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);

    mockMvc.perform(getRequest).andExpect(status().isOk())
        .andExpect(
            content().json(objectToJsonConvertor.writeValueAsString(expectedValueAllEmployeesList)))
        .andReturn();

  }

  /**
   * Method is used to test getAllEmployee Method
   * 
   * @throws Exception
   */
  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  public void testGetAll() throws Exception {

    RequestBuilder getAllrequest = MockMvcRequestBuilders.get("/employees")
        .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);

    when(service.getAllEmployee()).thenReturn(actualValueAllEmployeesList);

    mockMvc.perform(getAllrequest).andExpect(status().isOk())
        .andExpect(
            content().json(objectToJsonConvertor.writeValueAsString(expectedValueAllEmployeesList)))
        .andReturn();

  }

  /**
   * Test is used to test saveEmployee method
   * 
   * @throws Exception
   */
  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  public void testsaveCall() throws Exception {


    Response expectedResponse = new Response("Resource Created", "Created", expectedEmployeeValue);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/employees")
            .content(objectToJsonConvertor.writeValueAsString(actualEmployeeValue))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectToJsonConvertor.writeValueAsString(expectedResponse)));

    verify(service).saveEmployee(argumentCaptor.capture());

  }

  /**
   * Test used to test update method
   * 
   * @throws Exception
   */
  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  public void testUpdate() throws Exception {
    when(service.updateEmployee(actualEmployeeValue)).thenReturn(actualEmployeeValue);
    assertEquals(expectedEmployeeValue, actualEmployeeValue);
  }

  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  public void testUpdateGetCalled() throws Exception {
    Response expectedResponse = new Response("Resporce Updated", "Updated", expectedEmployeeValue);

    mockMvc
        .perform(MockMvcRequestBuilders.put("/employees")
            .content(objectToJsonConvertor.writeValueAsString(actualEmployeeValue))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectToJsonConvertor.writeValueAsString(expectedResponse)));

    verify(service).updateEmployee(argumentCaptor.capture());

  }


  /**
   * Test used to test delete method
   * 
   * @throws Exception
   */

  @Test
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  public void testDelete() throws Exception {

    int employeeID = anyInt();
    Response expectedResponse =
        new Response("Resource Removed", "Resource with id has been removed", null);


    RequestBuilder deleteRequest = MockMvcRequestBuilders.delete("/employees/" + employeeID)
        .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);


    mockMvc.perform(deleteRequest).andExpect(status().isOk())
        .andExpect(content().json(objectToJsonConvertor.writeValueAsString(expectedResponse)))
        .andReturn();

    verify(service, times(1)).deleteEmployee(employeeID);

  }

  /**
   * Test added to check access role
   * 
   * @throws Exception
   */
  @Test
  public void accessRoleProtected() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/employees").with(httpBasic("user", "password")))
        .andExpect(status().is4xxClientError());
  }

  /**
   * Test added to check security
   * 
   * @throws Exception
   */
  @Test
  public void accessGetEmployeeById() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/employees").with(httpBasic("admin", "password"))
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML))
        .andExpect(status().isOk());
  }


}
