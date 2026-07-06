package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.set_drone_activity.SetDroneActivityInput;
import be.howest.adria.application.croptek.set_drone_activity.SetDroneActivityUC;
import be.howest.adria.domain.Drone;
import be.howest.adria.domain.DroneActivity;
import be.howest.adria.infrastructure.repositories.tables.TestDronesRepo;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SetDroneActivityTest {

    private final DronesRepo repo = new TestDronesRepo();
    UUID droneId = UUID.randomUUID();
    Drone drone = new Drone(droneId);
    private HttpResponseStatus lastStatus;

    @BeforeEach
    void prepare(){
        repo.addDrone(drone);
    }

    @Test
    void testSettingDroneActivity(){
        SetDroneActivityUC uc = new SetDroneActivityUC(this::checkResults, repo);
        uc.execute(new SetDroneActivityInput(droneId, DroneActivity.WATERING));
    }

    @Test
    void testSettingActivityForNonExistentDrone() {
        UUID nonExistentDroneId = UUID.randomUUID();
        SetDroneActivityUC uc = new SetDroneActivityUC(this::captureStatus, repo);
        uc.execute(new SetDroneActivityInput(nonExistentDroneId, DroneActivity.WATERING));

        assertEquals(HttpResponseStatus.NOT_FOUND, lastStatus);
        assertFalse(repo.getDrone(nonExistentDroneId).isPresent());
    }

    private void checkResults(HttpResponseStatus result){
        assertEquals(HttpResponseStatus.NO_CONTENT, result);
        assertTrue(repo.getDrone(droneId).isPresent());
        assertEquals(DroneActivity.WATERING, repo.getDrone(droneId).get().getActivity());
    }

    private void captureStatus(HttpResponseStatus status) {
        lastStatus = status;
    }
}
