package com.employee.management.beans.configuration;

import java.lang.reflect.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.employee.management.service.EmployeeService;
import com.employee.management.service.proxy.EmployeeServiceProxy;
import lombok.RequiredArgsConstructor;

/**
 * This class uses Employee Proxy to manage dynamic changes between CDKEmployeeBean and
 * NorthBayEmployee Bean
 * 
 * @author umar.tahir
 *
 */

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeProxyConfiguration {

  @Autowired
  EmployeeProxyConfigurationData data;

  /**
   * The Method returns Proxy Service Interface for Employee Service which is aware of all the beans
   * of service
   *
   * @return
   */

  @Bean
  @ConditionalOnProperty(name = "allownbsbean", havingValue = "true")
  @Primary
  public EmployeeService getEmployeeServiceBean() {
    EmployeeServiceProxy serviceAdvice = new EmployeeServiceProxy(data);

    return (EmployeeService) Proxy.newProxyInstance(EmployeeService.class.getClassLoader(),
        new Class[] {EmployeeService.class}, serviceAdvice);
  }

}
