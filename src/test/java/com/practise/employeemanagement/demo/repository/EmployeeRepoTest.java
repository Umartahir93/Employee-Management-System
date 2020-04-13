package com.practise.employeemanagement.demo.repository;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.employee.management.model.EmployeeEntity;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.specifications.EmployeeSpecification;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepoTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private EmployeeRepository repo;

  @Test
  public void testFindByName() {
    List<EmployeeEntity> employees = repo.findByName("EmployeeTest");
    assertEquals(3, employees.size());
  }

  @Test
  public void testFindByNameWithSpecification() {
    assertEquals(3, repo.findAll(EmployeeSpecification.getEmployeeByName("EmployeeTest")).size());
  }



}
