package com.andreigog.apigateway.exception;

public class BadConfigurationException extends RuntimeException {

  public BadConfigurationException(String message) {
    super(message);
  }
}