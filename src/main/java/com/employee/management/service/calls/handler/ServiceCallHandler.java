package com.employee.management.service.calls.handler;

import java.lang.reflect.Method;
import com.employee.management.beans.configuration.EmployeeProxyConfigurationData;

/**
 * This handler's main responsibility to replace Conditional Dispatcher with Command
 * 
 * @author umar.tahir
 *
 */

public interface ServiceCallHandler {
  Object handle(Method method, Object[] args, EmployeeProxyConfigurationData data) throws Exception;
}
