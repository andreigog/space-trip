package com.andreigog.planet.security;

import java.util.Collection;

public class LocalContextHolder {
  private static ThreadLocal<RequestLocalContext> holder = new ThreadLocal<>();

  public static void set(RequestLocalContext requestLocalContext) {
    holder.set(requestLocalContext);
  }

  public static String getActiveUserId() {
    return holder.get().getActiveUserId();
  }

  public static Collection<Permission> getPermissions() {
    return holder.get().getActiveUserPermissions();
  }
}
