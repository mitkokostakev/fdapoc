package com.example.fda.demo.exception.handler;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Base GlobalExceptionHandler class for handling common exceptions being handled in the proper way
 * to display a corresponding {@link ErrorResponse} payload
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return getResponseEntity(ex.getLocalizedMessage(), ex.fillInStackTrace(), ex);
  }

  @NotNull
  private ResponseEntity<Object> getResponseEntity(
      String localizedMessage, Throwable throwable, Exception ex) {
    List<String> details = new ArrayList<>();
    details.add(localizedMessage);
    ErrorResponse error = new ErrorResponse("Validation Failed", details);
    log.error(localizedMessage, throwable);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Server Error", details);
    log.error(ex.getLocalizedMessage(), ex.fillInStackTrace());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorResponse error = new ErrorResponse("Validation Failed", details);
    log.error(ex.getLocalizedMessage(), ex.fillInStackTrace());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex, WebRequest request) {
    return getResponseEntity(ex.getLocalizedMessage(), ex.fillInStackTrace(), ex);
  }
}
