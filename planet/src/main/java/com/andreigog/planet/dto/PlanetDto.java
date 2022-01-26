package com.andreigog.planet.dto;

import com.andreigog.planet.integration.crew.Crew;
import com.andreigog.planet.model.Planet;
import com.andreigog.planet.model.PlanetStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class PlanetDto {
  private String id;
  private String name;
  private String image;
  private PlanetStatus status;
  private Crew crew;

  public static PlanetDto fromPlanet(Planet planet, @Nullable Crew crew) {
    return new PlanetDto(planet.getId(), planet.getName(), planet.getImage(), planet.getStatus(), crew);
  }

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public Optional<Crew> getCrew() {
    return Optional.ofNullable(crew);
  }
}
