package com.andreigog.planet.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class CrewDto {
  private String id;
  private String captainId;
  private Set<String> robots;
}
