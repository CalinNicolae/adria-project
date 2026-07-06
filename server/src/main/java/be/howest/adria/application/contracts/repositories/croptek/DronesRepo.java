package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Drone;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DronesRepo {

    void addDrone(Drone drone) throws RepoAccessException;

    Set<Drone> getAllDrones() throws RepoAccessException;

    Set<Drone> getAllDronesForUserId(UUID userId);

    Optional<Drone> getDrone(UUID id) throws RepoAccessException;

    void update(Drone drone) throws RepoAccessException;

    Optional<Drone> getAvailableDrones();

    void clear();
}
