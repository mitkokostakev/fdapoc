package com.example.fda.demo.exception.handler;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Class defining data model for Error Response */
@Data
@AllArgsConstructor
public class ErrorResponse {

  /** General error message about nature of error */
  private String message;

  /** Specific errors in API request processing */
  private List<String> details;
}
