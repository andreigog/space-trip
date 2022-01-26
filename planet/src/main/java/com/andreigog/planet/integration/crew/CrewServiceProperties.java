package com.andreigog.planet.integration.crew;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "crew-service")
public class CrewServiceProperties {
  private String crewsUrl;
  private String crewByIdUrl;
}
