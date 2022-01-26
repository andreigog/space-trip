package com.andreigog.planet.transfer;

import com.andreigog.planet.model.PlanetStatus;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CreatePlanetRequest {
  @NotBlank
  private String name;
  @NotBlank
  private String image;
  private PlanetStatus status;
}
