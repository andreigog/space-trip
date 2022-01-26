package com.andreigog.planet.service;

import com.andreigog.planet.dto.PlanetDto;
import com.andreigog.planet.exception.ConflictException;
import com.andreigog.planet.exception.NotFoundException;
import com.andreigog.planet.integration.crew.Crew;
import com.andreigog.planet.integration.crew.RemoteCrewService;
import com.andreigog.planet.model.Planet;
import com.andreigog.planet.model.PlanetStatus;
import com.andreigog.planet.repository.PlanetRepository;
import com.andreigog.planet.transfer.CreatePlanetRequest;
import com.andreigog.planet.transfer.FetchPlanetsRequest;
import com.andreigog.planet.transfer.PatchPlanetRequest;
import com.andreigog.planet.transfer.PlanetsResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import static java.util.Collections.emptyMap;

@Service
@Slf4j
public class PlanetService {

  private final PlanetRepository planetRepository;
  private final RemoteCrewService remoteCrewService;

  @Autowired
  public PlanetService(PlanetRepository planetRepository, RemoteCrewService remoteCrewService) {
    this.planetRepository = planetRepository;
    this.remoteCrewService = remoteCrewService;
  }

  public PlanetDto createPlanet(CreatePlanetRequest request) {
    LOGGER.debug("createPlanet >> request = {}", request);

    Planet planet = new Planet(request.getName(), request.getImage(), null, null);
    planetRepository.insert(planet);
    return PlanetDto.fromPlanet(planet, null);
  }

  public PlanetsResponse fetchPlanets(FetchPlanetsRequest request) {
    LOGGER.debug("createPlanet >> includeCrew = {}", request);

    Page<Planet> planets = planetRepository.findAll(PageRequest.of(request.getPage(), request.getPageSize()));
    long planetCount = planetRepository.count();

    Map<String, Crew> crewsById = request.isIncludeCrew()
        ? getCrewsByIds(getCrewsIds(planets))
        : emptyMap();
    Collection<PlanetDto> planetDtos = planets.stream()
        .map(planet -> PlanetDto.fromPlanet(planet, planet.getCrewId().map(crewsById::get).orElse(null)))
        .collect(Collectors.toList());

    return new PlanetsResponse(planetDtos, planetCount);
  }

  public PlanetDto updatePlanet(String planetId, PatchPlanetRequest request) {
    Planet planet = planetRepository.findById(planetId).orElseThrow(() -> new NotFoundException("Planet not found!"));

    request.getStatus().ifFilledOrElse(
        status -> this.updatePlanetStatus(planet, status),
        () -> {
        },
        planet::defaultStatus
    );
    return PlanetDto.fromPlanet(planetRepository.save(planet), null);
  }

  private void updatePlanetStatus(Planet planet, PlanetStatus status) {
    if (!planet.getCrewId().isPresent() && status.isNeedsCrew()) {
      throw new ConflictException("Requested planet status needs a crew assigned to the planet!");
    }
    planet.setStatus(status);
  }

  private Map<String, Crew> getCrewsByIds(Set<String> ids) {
    if (ids.isEmpty()) return emptyMap();
    return remoteCrewService.getCrews()
        .getItems()
        .stream()
        .filter(crew -> ids.contains(crew.getId()))
        .collect(Collectors.toMap(Crew::getId, Function.identity()));
  }

  private Set<String> getCrewsIds(Page<Planet> planets) {
    return planets.stream()
        .map(Planet::getCrewId)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toSet());
  }

  public void assignCrew(String planetId, String crewId) {
    Planet planet = planetRepository.findById(planetId).orElseThrow(() -> new NotFoundException("Planet not found!"));
    remoteCrewService.getCrew(crewId).orElseThrow(() -> new NotFoundException("Crew not found!"));

    planet.setCrewId(crewId);
    planetRepository.save(planet);
  }
}
