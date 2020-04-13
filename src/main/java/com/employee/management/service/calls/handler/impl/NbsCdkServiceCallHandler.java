package com.employee.management.service.calls.handler.impl;

import java.util.HashMap;
import java.util.Map;
import com.employee.management.properties.EmployeeProperties;
import com.employee.management.service.calls.handler.ServiceCallHandler;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NbsCdkServiceCallHandler {
  private volatile static Map<String, ServiceCallHandler> resource;

  public static Map<String, ServiceCallHandler> getInstance() {
    if (resource == null) {
      synchronized (NbsCdkServiceCallHandler.class) {
        if (resource == null) {
          populateHashResource();
        }
      }

    }
    return resource;
  }

  private static void populateHashResource() {
    resource = new HashMap<>();
    resource.put(EmployeeProperties.SERVICE_PROXY_NBS_CDK_SERVICE, allowedServiceNbsAndCdk());
    resource.put(EmployeeProperties.SERVICE_PROXY_NBS_SERVICE, allowedServiceNbs());
    resource.put(EmployeeProperties.SERVICE_PROXY_CDK_SERVICE, allowedServiceCdk());
  }

  // ======= Learning purpose ==========

  // In my example calls to both services simultaneously are redundant
  // You can modify logic according to your business needs
  // For me both services perform CRUDoperations on same DB table

  private static ServiceCallHandler allowedServiceNbsAndCdk() {
    return (method, args, data) -> {

      log.info("==== Both the services will be get called ====");

      log.info("==== Invoking NBS service ====");
      method.invoke(data.getNorthbay(), args);

      log.info("==== Invoking CDK service ====");
      log.info("==== Returning result service ====");
      return method.invoke(data.getCdk(), args);

    };

  }

  private static ServiceCallHandler allowedServiceNbs() {
    return (method, args, data) -> {
      log.info("==== Invoking NBS service ====");
      return method.invoke(data.getNorthbay(), args);
    };
  }

  private static ServiceCallHandler allowedServiceCdk() {
    return (method, args, data) -> {
      log.info("==== Invoking CDk service ====");
      return method.invoke(data.getCdk(), args);
    };
  }

}
