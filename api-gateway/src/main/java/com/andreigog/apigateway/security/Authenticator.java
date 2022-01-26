package com.andreigog.apigateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;

public interface Authenticator<C extends Credentials> {

  C authenticate(ServerHttpRequest request);
}