package com.employee.management.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Model class for Employee
 * 
 * @author umar.tahir
 *
 */
@Data
@Entity
@Accessors(fluent = true, chain = true)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer employeeId;

  @Column(name = "name", nullable = false, length = 10)
  private String name;

  private String address;

  private String qualifications;

  private Integer salary;

  private Integer experienceInYears;

  private String companyName;

}
