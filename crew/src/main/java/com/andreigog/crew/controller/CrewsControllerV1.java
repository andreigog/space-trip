package com.andreigog.crew.controller;

import com.andreigog.crew.domain.Crew;
import com.andreigog.crew.domain.CrewsResponse;
import com.andreigog.crew.domain.Robot;
import com.andreigog.crew.exception.NotFoundException;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/crews")
@Slf4j
public class CrewsControllerV1 {

  private static final Map<String, Crew> CREWS = ImmutableMap.of(
      "61efca7a127d4e0c73f213ce", new Crew("61efca7a127d4e0c73f213ce", "Atlantis", "61efd32351844d74a7ac1bb5", Arrays.asList(Robot.WALLE, Robot.TARS)),
      "61efca7a127d4e0c73f213c4", new Crew("61efca7a127d4e0c73f213c4", "Hail Mary", "61efd32351844d74a7ac1bb6", Arrays.asList(Robot.BENDER, Robot.TARS)),
      "61efca7a127d4e0c73f213c5", new Crew("61efca7a127d4e0c73f213c5", "Chomolungma", "61efd32351844d74a7ac1bb7", Arrays.asList(Robot.R2D2, Robot.TARS, Robot.WALLE, Robot.R2D2))
  );

  @GetMapping
  public CrewsResponse fetchCrews() {
    log.info("fetchCrews");

    return new CrewsResponse(CREWS.values(), CREWS.values().size());
  }

  @GetMapping(value = "/{crewId}")
  public Crew getCrew(@PathVariable String crewId) {
    log.info("getCrew >> crewId = {}", crewId);

    return Optional.ofNullable(CREWS.get(crewId))
        .orElseThrow(() -> new NotFoundException("Crew not found!"));
  }
}
