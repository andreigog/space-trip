package com.andreigog.planet.integration.crew;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Crew {
  private String id;
  private String name;
  private String captainId;
  private Collection<String> robots;
}
