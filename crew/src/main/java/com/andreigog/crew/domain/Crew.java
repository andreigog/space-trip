package com.andreigog.crew.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Crew {
  private String id;
  private String name;
  private String captainId;
  private List<Robot> robots;
}
