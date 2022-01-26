package com.andreigog.planet.security;

import java.util.Arrays;
import lombok.Getter;

public enum PermissionType {
  READ("read"),
  CREATE("create"),
  DELETE("delete"),
  UPDATE("update");

  @Getter
  private String value;

  PermissionType(String value) {
    this.value = value;
  }

  public static PermissionType fromValue(String value) {
    return Arrays.stream(PermissionType.values())
        .filter(permissionType -> permissionType.getValue().equals(value))
        .findFirst().orElse(null);
  }
}
