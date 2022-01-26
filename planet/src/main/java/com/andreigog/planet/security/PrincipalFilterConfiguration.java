package com.andreigog.planet.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PrincipalFilterConfiguration {
  @Bean
  public PrincipalFilter principalFilter() {
    return new PrincipalFilter();
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {

    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setEnabled(false);

    registrationBean.setFilter(principalFilter());

    LOGGER.info("Registered Principal filter with Spring");

    return registrationBean;
  }
}
