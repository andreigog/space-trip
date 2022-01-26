package com.andreigog.apigateway.exception;

public class BadCredentialsException extends RuntimeException {

  public BadCredentialsException(String message) {
    super(message);
  }
}