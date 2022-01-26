package com.andreigog.planet.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Permission {
  private String entity;
  private PermissionType type;

  public static Permission fromRawPermission(String rawPermission) {
    String[] values = rawPermission.split(":");
    return new Permission(values[1], PermissionType.fromValue(values[0]));
  }

  public static Permission readPlanets() {
    return new Permission("planets", PermissionType.READ);
  }
}
