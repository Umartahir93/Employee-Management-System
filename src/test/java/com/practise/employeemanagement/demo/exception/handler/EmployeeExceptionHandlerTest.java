package com.practise.employeemanagement.demo.exception.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.employee.management.domain.ErrorResponse;
import com.employee.management.exceptions.EmployeeException;
import com.employee.management.exceptions.handler.EmployeeExceptionHandler;

/**
 * Test added to check Employee Exception
 * 
 * @author umar.tahir
 *
 */
public class EmployeeExceptionHandlerTest {

  EmployeeExceptionHandler handler = new EmployeeExceptionHandler();

  /**
   * Test case that was added to do Exception Handling
   */

  @Test
  public void testExceptionToDoHandler() {
    ResponseEntity<ErrorResponse> expected = initResponseEntity();
    ResponseEntity<ErrorResponse> result = handler.handleEmployeeException(initException());

    assertEquals(expected.getStatusCode(), result.getStatusCode());
    assertEquals(expected.getBody(), result.getBody());
  }

  private ResponseEntity<ErrorResponse> initResponseEntity() {
    return new ResponseEntity<>(initErrorResponse(), HttpStatus.BAD_REQUEST);

  }

  private ErrorResponse initErrorResponse() {
    return ErrorResponse.builder().errorCode(400).message("Error Message")
        .description("Error Description").build();

  }

  private EmployeeException initException() {
    return EmployeeException.builder().code(HttpStatus.BAD_REQUEST).description("Error Description")
        .message("Error Message").build();
  }

}
