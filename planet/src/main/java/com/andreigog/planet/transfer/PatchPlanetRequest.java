package com.andreigog.planet.transfer;

import com.andreigog.planet.model.PlanetStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatchPlanetRequest {
  Field<PlanetStatus> status = Field.empty();

  public void setStatus(String status) {
    this.status = Field.ofNullable(PlanetStatus.fromValue(status));
  }
}
