package com.employee.management.exceptions;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class returns runtime exceptions for employee
 * 
 * @author umar.tahir
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
public class EmployeeException extends RuntimeException {
  private final HttpStatus code;
  private final String description;
  private final String message;

}
