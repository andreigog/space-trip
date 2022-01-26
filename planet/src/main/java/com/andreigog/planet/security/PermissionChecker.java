package com.andreigog.planet.security;

import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class PermissionChecker {

  public boolean hasPermissions(String permissions) {
    Collection<Permission> requiredPermissions = PermissionsParser.parse(permissions);
    return LocalContextHolder.getPermissions().contains(requiredPermissions);
  }
}
