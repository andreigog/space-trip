package com.andreigog.apigateway.config;

import com.andreigog.apigateway.exception.BadConfigurationException;
import com.andreigog.apigateway.security.Authenticator;
import com.andreigog.apigateway.security.SecurityAuthProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class SpringConfiguration {

  private SecurityAuthProperties properties;

  @Autowired
  public SpringConfiguration(SecurityAuthProperties properties) {
    this.properties = properties;
  }

  @PostConstruct
  public void postConstruct() {
    properties.getRoutes().forEach((s, route) -> {
      for (Class clazz : route.getAuthenticatorClasses()) {
        if (!Authenticator.class.isAssignableFrom(clazz)) {
          log.error("Route {} contains an invalid authenticator.", route.getId());
          throw new BadConfigurationException("Invalid authenticator.");
        }
      }
    });
  }
}