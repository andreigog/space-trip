package com.andreigog.planet.web;

import com.andreigog.planet.exception.ConflictException;
import com.andreigog.planet.exception.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleException(NotFoundException exception) {
    LOGGER.info("Not found exception, returning not found.", exception);
    return new ErrorResponse(exception.getMessage());
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse handleException(ConflictException exception) {
    LOGGER.info("Conflict exception, returning conflict.", exception);
    return new ErrorResponse(exception.getMessage());
  }
}