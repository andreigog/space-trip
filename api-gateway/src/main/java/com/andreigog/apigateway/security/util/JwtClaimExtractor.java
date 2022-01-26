package com.andreigog.apigateway.security.util;

import org.springframework.security.oauth2.jwt.Jwt;

public class JwtClaimExtractor {

  private static final String SUBJECT_CLAIM = "sub";
  private static final String SCOPE_CLAIM = "scope";


  public static String extractUserId(Jwt jwt) {
    return jwt.getClaim(SUBJECT_CLAIM).toString().split("\\|")[1];
  }

  public static String extractPermissions(Jwt jwt) {
    return jwt.getClaim(SCOPE_CLAIM);
  }
}
