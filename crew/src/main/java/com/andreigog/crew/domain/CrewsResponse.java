package com.andreigog.crew.domain;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CrewsResponse {
  private Collection<Crew> items;
  private int total;
}
