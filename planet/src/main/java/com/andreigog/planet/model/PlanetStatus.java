package com.andreigog.planet.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlanetStatus {
  OK("OK", true),
  NOT_OK("!OK", true),
  TODO("TODO", false),
  EN_ROUTE("En route", true);

  private String value;
  @Getter
  private boolean needsCrew;

  @JsonValue
  public String getValue() {
    return value;
  }

  @JsonCreator
  public static PlanetStatus fromValue(String value) {
    return Stream.of(PlanetStatus.values())
        .filter(v -> v.getValue().equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid planet status: %s.", value)));
  }
}
