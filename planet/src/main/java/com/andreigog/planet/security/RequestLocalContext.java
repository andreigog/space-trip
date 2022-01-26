package com.andreigog.planet.security;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestLocalContext {
  private String activeUserId;
  private Collection<Permission> activeUserPermissions;
}
