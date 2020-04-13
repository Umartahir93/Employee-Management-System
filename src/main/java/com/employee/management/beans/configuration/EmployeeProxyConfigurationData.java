package com.employee.management.beans.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.employee.management.service.impl.CdkEmployeeService;
import com.employee.management.service.impl.NorthBayEmployeeService;

/**
 * Employee Configuration Data which holds application properties and refrence to implemnetation for
 * Employee Service
 * 
 * @author umar.tahir
 *
 */
@Component
public class EmployeeProxyConfigurationData {
  @Autowired(required = false)
  private CdkEmployeeService cdk;

  @Autowired(required = false)
  private NorthBayEmployeeService northbay;

  @Value("${allownbsbean}")
  private boolean nbsBeanAllowed;

  @Value("${allowcdkbean}")
  private boolean cdkBeanAllowed;


  public EmployeeProxyConfigurationData() {}

  public CdkEmployeeService getCdk() {
    return cdk;
  }

  public NorthBayEmployeeService getNorthbay() {
    return northbay;
  }

  public boolean isNbsBeanAllowed() {
    return nbsBeanAllowed;
  }

  public boolean isCdkBeanAllowed() {
    return cdkBeanAllowed;
  }

}
