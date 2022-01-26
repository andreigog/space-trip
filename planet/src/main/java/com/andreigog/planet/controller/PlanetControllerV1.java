package com.andreigog.planet.controller;

import com.andreigog.planet.dto.PlanetDto;
import com.andreigog.planet.transfer.AssignCrewRequest;
import com.andreigog.planet.transfer.CreatePlanetRequest;
import com.andreigog.planet.transfer.FetchPlanetsRequest;
import com.andreigog.planet.transfer.PatchPlanetRequest;
import com.andreigog.planet.service.PlanetService;
import com.andreigog.planet.transfer.PlanetsResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/planets")
@Slf4j
public class PlanetControllerV1 {

  private final PlanetService planetService;

  @Autowired
  public PlanetControllerV1(PlanetService planetService) {
    this.planetService = planetService;
  }

  @GetMapping
  public PlanetsResponse fetchPlanets(@ModelAttribute @Valid FetchPlanetsRequest request) {
    LOGGER.info("fetchPlanets >> request = {}", request);

    return planetService.fetchPlanets(request);
  }

  @PostMapping
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  public PlanetDto createPlanet(@RequestBody @Valid CreatePlanetRequest request) {
    LOGGER.info("createPlanet >> request = {}", request);

    return planetService.createPlanet(request);
  }

  @PatchMapping(value = "/{planetId}")
  public PlanetDto patchPlanet(@PathVariable String planetId, @RequestBody @Valid PatchPlanetRequest request) {
    LOGGER.info("patchPlanet >> planetId = {}, request = {}", planetId, request);

    return planetService.updatePlanet(planetId, request);
  }

  @PutMapping("/{planetId}/assign-crew")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void assignCrew(@PathVariable String planetId, @RequestBody @Valid AssignCrewRequest request) {
    LOGGER.info("assignCrew >> planetId = {}, request = {}", planetId, request);

    planetService.assignCrew(planetId, request.getCrewId());
  }
}
