package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.DroneFailure;
import java.util.List;
import java.util.UUID;

public interface DroneFailuresRepo {

    List<DroneFailure> getDroneFailures(UUID droneId) throws RepoAccessException;

    List<DroneFailure> getAllDroneFailures();

    void addFailure(DroneFailure failure) throws RepoAccessException;

}
