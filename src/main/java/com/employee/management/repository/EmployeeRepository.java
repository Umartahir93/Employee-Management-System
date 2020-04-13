package com.employee.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.employee.management.model.EmployeeEntity;

/**
 * Repository interface for Employee
 * 
 * @author umar.tahir
 *
 */
@Repository
public interface EmployeeRepository
    extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {
  public List<EmployeeEntity> findByName(String name);

}
