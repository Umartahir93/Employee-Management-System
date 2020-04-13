package com.employee.management.exceptions.handler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.employee.management.controller.impl.EmployeeControllerImpl;
import com.employee.management.domain.ErrorResponse;
import com.employee.management.exceptions.EmployeeException;

/**
 * This exception handler class for Employee Controller
 * 
 * @author umar.tahir
 *
 */

@ControllerAdvice(assignableTypes = EmployeeControllerImpl.class)
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(EmployeeException.class)
  public ResponseEntity<ErrorResponse> handleEmployeeException(EmployeeException ex) {
    return new ResponseEntity<>(ErrorResponse.builder().errorCode(ex.getCode().value())
        .message(ex.getMessage()).description(ex.getDescription()).build(), ex.getCode());
  }


  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", status.value());

    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(x -> x.getDefaultMessage()).collect(Collectors.toList());

    body.put("errors", errors);

    return new ResponseEntity<>(body, headers, status);

  }


  @Override
  public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value())
        .message("Bad Request").description("Request Not Valid").build(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
    return new ResponseEntity<>(
        ErrorResponse.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("Something goes wrong while persisting data")
            .description("Something goes wrong. Please contact Northbay solutions. ").build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }



}


