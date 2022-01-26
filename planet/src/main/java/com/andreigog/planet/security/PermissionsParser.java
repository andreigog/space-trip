package com.andreigog.planet.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class PermissionsParser {
  public static Collection<Permission> parse(String input) {
    return Arrays.stream(input.split(" "))
        .map(Permission::fromRawPermission)
        .collect(Collectors.toSet());
  }
}
