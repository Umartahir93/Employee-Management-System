package com.employee.management.beans.configuration;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.employee.management.properties.EmployeeProperties;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration properties provided to add custom details for EmployeeApi
 * 
 * @author umar.tahir
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket swaggerConfiguration() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(
            RequestHandlerSelectors.basePackage("com.practise.employeemanagement.demo.controller"))
        .build().apiInfo(getApiInfoDetails());
  }

  /**
   * Method returns API Info object used to set specific properties for my API
   * 
   * @return API Info object
   */

  private ApiInfo getApiInfoDetails() {
    return new ApiInfo(EmployeeProperties.EMPLOYEE_DETAILS_API,
        EmployeeProperties.EMPLOYEE_DETAILS_API_Description,
        EmployeeProperties.EMPLOYEE_DETAILS_API_VERSION,
        EmployeeProperties.EMPLOYEE_DETAILS_API_ALLLOWED_TO_USE,
        new springfox.documentation.service.Contact(
            EmployeeProperties.EMPLOYEE_DETAILS_DEVELOPER,
            EmployeeProperties.EMPLOYEE_DETAILS_WEBSITE,
            EmployeeProperties.EMPLOYEE_DETAILS_EMAIL),
        EmployeeProperties.EMPLOYEE_DETAILS_API_LISENCE, "", Collections.emptyList());
  }
}


