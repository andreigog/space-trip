package com.andreigog.planet.transfer;

import com.andreigog.planet.dto.PlanetDto;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlanetsResponse {
  private Collection<PlanetDto> items;
  private long total;
}
