package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.DroneFailuresRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.DroneFailure;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class TestDroneFailuresRepo implements DroneFailuresRepo {

    private final List<DroneFailure> droneFailures = new ArrayList<>();

    @Override
    public List<DroneFailure> getDroneFailures(UUID droneId) throws RepoAccessException {
        final List<DroneFailure> failuresFound = new ArrayList<>();

        for(DroneFailure droneFailure : droneFailures) {
            if(droneId.equals(droneFailure.getDroneId())) {
                failuresFound.add(droneFailure);
            }
        }

        return failuresFound;
    }

    @Override
    public List<DroneFailure> getAllDroneFailures() {
        return Collections.unmodifiableList(droneFailures);
    }

    @Override
    public void addFailure(DroneFailure failure) throws RepoAccessException {
        if(!containsId(getDroneFailures(failure.getDroneId()), failure.getFailureTime())) {
            droneFailures.add(failure);
        }
    }

    private boolean containsId(List<DroneFailure> droneFailures, Instant failureTime) {
        for(DroneFailure droneFailure : droneFailures) {
            if(droneFailure.getFailureTime().equals(failureTime)) {
                return true;
            }
        }

        return false;
    }

}
