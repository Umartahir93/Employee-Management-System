package com.employee.management.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto for Employee Resource
 * 
 * @author umar.tahir
 *
 */

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@Builder(toBuilder = true)

@ApiModel(description = "Details about the Employee Object")
public class Employee {

  @ApiModelProperty(notes = "Id is always unique")
  private int id;

  @ApiModelProperty(notes = "Name is mandatory filed to add Employee in Systsem")
  private String name;

  @ApiModelProperty(notes = "Address is mandatory filed to add Employee in System")
  private String address;

  @ApiModelProperty(notes = "Qualifications is mandatory filed to add Employee in Systsem")
  private String qualifications;

  @ApiModelProperty(notes = "Salary is mandatory filed to add Employee in Systsem")
  @Min(value = 1000, message = "Income should not be less than 1000")
  @Max(value = 100000, message = "Income should not be greater than 100000")
  private int salary;

  @ApiModelProperty(notes = "Years in experience")
  private int experienceInYears;

  @ApiModelProperty(notes = "Company name tells which api implementation is running")
  private String companyName;



}
