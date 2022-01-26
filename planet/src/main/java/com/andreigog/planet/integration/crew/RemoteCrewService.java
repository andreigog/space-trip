package com.andreigog.planet.integration.crew;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RemoteCrewService {

  private final RestTemplate restTemplate;
  private final CrewServiceProperties crewServiceProperties;

  public RemoteCrewService(RestTemplate restTemplate, CrewServiceProperties crewServiceProperties) {
    this.restTemplate = restTemplate;
    this.crewServiceProperties = crewServiceProperties;
  }

  public CrewsResponse getCrews() {
    String requestUrl = crewServiceProperties.getCrewsUrl();
    return restTemplate.exchange(requestUrl, HttpMethod.GET, null, CrewsResponse.class)
        .getBody();
  }

  public Optional<Crew> getCrew(String crewId) {
    String requestUrl = crewServiceProperties.getCrewByIdUrl();
    Map<String, String> params = Collections.singletonMap("crewId", crewId);

    ResponseEntity<Crew> responseEntity = restTemplate
        .exchange(requestUrl, HttpMethod.GET, null, Crew.class, params);
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      return Optional.ofNullable(responseEntity.getBody());
    } else {
      return Optional.empty();
    }
  }
}
