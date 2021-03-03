package com.renmoney.assessment.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    log.error("Exception => ",ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<Object> handleIllegalArgumentExceptions(Exception ex, WebRequest request) {
    log.error("IllegalArgumentException => ",ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ConversionFailedException.class)
  public final ResponseEntity<Object> handleAllConversionFailedExceptions(Exception ex, WebRequest request) {
    log.error("ConversionFailedException => ",ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(ConverterNotFoundException.class)
  public final ResponseEntity<Object> handleAllConverterNotFoundExceptions(Exception ex, WebRequest request) {
    log.error("ConverterNotFoundException => ",ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public final ResponseEntity<Object> handleMethodArgumentTypeMismatch(Exception ex, WebRequest request) {
    log.error("MethodArgumentTypeMismatchException => ",ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    log.error("HttpMessageNotReadableException => ",ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

}