package com.employee.management.specifications;

import org.springframework.data.jpa.domain.Specification;
import com.employee.management.model.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeSpecification {

  public Specification<EmployeeEntity> getEmployeeByName(String personName) {
    return (root, query, cb) -> cb.equal(root.get("name"), personName);
  }

}
