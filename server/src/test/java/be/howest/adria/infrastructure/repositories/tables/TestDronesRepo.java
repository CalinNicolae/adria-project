package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Drone;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TestDronesRepo implements DronesRepo {

    private final Set<Drone> drones = new HashSet<>();

    @Override
    public void addDrone(Drone drone) throws RepoAccessException {
        final Optional<Drone> droneFound = getDrone(drone.getId());

        if(droneFound.isEmpty()) {
            drones.add(drone);
        }
    }

    @Override
    public Set<Drone> getAllDrones() throws RepoAccessException {
        return Collections.unmodifiableSet(drones);
    }

    @Override
    public Set<Drone> getAllDronesForUserId(UUID userId) {
        final Set<Drone> dronesFound = new HashSet<>();

        for(Drone drone : drones) {
            final Optional<UUID> droneUserId = drone.getUserId();

            if(droneUserId.isPresent() && droneUserId.get().equals(userId)) {
                dronesFound.add(drone);
            }
        }

        return Collections.unmodifiableSet(dronesFound);
    }

    @Override
    public Optional<Drone> getDrone(UUID id) throws RepoAccessException {
        for(Drone drone : drones) {
            if(drone.getId().equals(id)) {
                return Optional.of(drone);
            }
        }

        return Optional.empty();
    }

    @Override
    public void update(Drone drone) throws RepoAccessException {
        drones.remove(drone);
        drones.add(drone);
    }

    @Override
    public Optional<Drone> getAvailableDrones() {
        Set<Drone> availableDrones = drones.stream().filter(drone -> drone.getUserId().equals(Optional.empty())).collect(Collectors.toSet());

        return availableDrones.stream().findFirst();
    }

    @Override
    public void clear(){
        drones.clear();
    }

}
