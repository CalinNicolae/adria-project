package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.get_all_drones.GetAllDronesOutput;
import be.howest.adria.application.croptek.get_all_drones.GetAllDronesUC;
import be.howest.adria.domain.Drone;
import be.howest.adria.domain.DroneActivity;
import be.howest.adria.infrastructure.repositories.tables.TestDronesRepo;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class GetAllDronesTest {

    private final DronesRepo dronesRepo = new TestDronesRepo();

    private final Drone drone1 = new Drone(
            UUID.randomUUID(),LocalDate.now(),
            true,
            false,
            76,
            DroneActivity.HARVESTING,
            Optional.empty());
    private final Drone drone2 = new Drone(
            UUID.randomUUID(),LocalDate.now(),
            false,
            true,
            16,
            DroneActivity.PASSIVE,
            Optional.empty());

    @BeforeEach
    void prepare() {
        dronesRepo.addDrone(drone1);
        dronesRepo.addDrone(drone2);
    }

    @Test
    void getAllDrones() {
        final GetAllDronesUC uc = new GetAllDronesUC(this::evaluate, dronesRepo);
        uc.execute(null);
    }

    private void evaluate(GetAllDronesOutput data) {
        Assertions.assertTrue(data.contains(drone1) && data.contains(drone2));
    }

}
