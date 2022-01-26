package com.andreigog.planet.model;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planets")
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Planet extends BaseDomainEntity {
  private String name;
  private String image;
  private PlanetStatus status;
  private String crewId;

  public Optional<String> getCrewId() {
    return Optional.ofNullable(crewId);
  }

  public void defaultStatus() {
    this.status = PlanetStatus.TODO;
  }
}
