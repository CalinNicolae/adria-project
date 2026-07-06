package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.get_user_drones.GetUserDronesInput;
import be.howest.adria.application.croptek.get_user_drones.GetUserDronesOutput;
import be.howest.adria.application.croptek.get_user_drones.GetUserDronesUC;
import be.howest.adria.domain.Drone;
import be.howest.adria.domain.DroneActivity;
import be.howest.adria.infrastructure.repositories.tables.TestDronesRepo;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetUserDronesTest {

    private final DronesRepo dronesRepo = new TestDronesRepo();

    private final UUID userId1 = UUID.randomUUID();
    private final UUID userId2 = UUID.randomUUID();

    private final Drone drone1 = new Drone(
            UUID.randomUUID(), LocalDate.now(),
            true,
            false,
            33,
            DroneActivity.HARVESTING,
            Optional.of(userId1));
    private final Drone drone2 = new Drone(
            UUID.randomUUID(),LocalDate.now(),
            true,
            true,
            96,
            DroneActivity.PLANTING,
            Optional.of(userId2));
    private final Drone drone3 = new Drone(
            UUID.randomUUID(),LocalDate.now(),
            true,
            false,
            99,
            DroneActivity.WATERING,
            Optional.of(userId2));

    @BeforeEach
    void prepare() {
        dronesRepo.addDrone(drone1);
        dronesRepo.addDrone(drone2);
        dronesRepo.addDrone(drone3);
    }

    @Test
    void getUserDrones() {
        final GetUserDronesUC uc = new GetUserDronesUC(this::evaluate_getUserDrones, dronesRepo);
        uc.execute(new GetUserDronesInput(userId2));
    }

    private void evaluate_getUserDrones(GetUserDronesOutput data) {
        Assertions.assertTrue(!data.contains(drone1) && data.contains(drone2) && data.contains(drone3));
    }

}
