package com.employee.management.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.BiFunction;
import com.employee.management.beans.configuration.EmployeeProxyConfigurationData;
import com.employee.management.properties.EmployeeProperties;
import com.employee.management.service.calls.handler.ServiceCallHandler;
import com.employee.management.service.calls.handler.impl.NbsCdkServiceCallHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * This is service proxy class added to extend functionality of service
 * 
 * @author umar.tahir
 *
 */

@Slf4j
public class EmployeeServiceProxy implements InvocationHandler {

  Map<String, ServiceCallHandler> commandMap;
  EmployeeProxyConfigurationData data;

  public EmployeeServiceProxy(EmployeeProxyConfigurationData data) {
    this.data = data;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    log.info("==== Proxy just gets called ====");
    commandMap = NbsCdkServiceCallHandler.getInstance();

    try {
      return commandMap.get(getCondition().apply(data.isNbsBeanAllowed(), data.isCdkBeanAllowed()))
          .handle(method, args, data);

    } catch (InvocationTargetException ex) {
      throw ex.getCause();
    }
  }


  private BiFunction<Boolean, Boolean, String> getCondition() {
    return (nbsAllowed, cdkAllowed) -> {

      if (Boolean.TRUE.equals(nbsAllowed) && Boolean.TRUE.equals(cdkAllowed)) {
        return EmployeeProperties.SERVICE_PROXY_NBS_CDK_SERVICE;
      }

      return Boolean.TRUE.equals(nbsAllowed) ? EmployeeProperties.SERVICE_PROXY_NBS_SERVICE
          : EmployeeProperties.SERVICE_PROXY_CDK_SERVICE;

    };

  }

}


