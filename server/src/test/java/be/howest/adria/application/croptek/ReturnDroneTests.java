package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.return_drone.ReturnDroneInput;
import be.howest.adria.application.croptek.return_drone.ReturnDroneUC;
import be.howest.adria.domain.Drone;
import be.howest.adria.domain.DroneActivity;
import be.howest.adria.infrastructure.repositories.tables.TestDronesRepo;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReturnDroneTests {

    DronesRepo repo = new TestDronesRepo();
    UUID user = UUID.randomUUID();
    Drone drone = new Drone(UUID.randomUUID(), LocalDate.now(), true, false, 100, DroneActivity.PASSIVE, Optional.of(user));
    HttpResponseStatus lastResponse;
    @BeforeEach
    void prepare(){
        repo.addDrone(drone);
    }

    @Test
    void testReturningDrone(){
        ReturnDroneUC uc = new ReturnDroneUC(this::checkResponse, repo);
        uc.execute(new ReturnDroneInput(drone.getId()));
    }

    private void checkResponse(HttpResponseStatus response){
        assertEquals(HttpResponseStatus.NO_CONTENT, response);
        assertFalse(repo.getAllDronesForUserId(user).contains(drone));
        assertTrue(repo.getAllDrones().contains(drone));
    }

    @Test
    void testReturningNonExistentDrone() {
        UUID nonExistentDroneId = UUID.randomUUID();
        ReturnDroneUC uc = new ReturnDroneUC(this::captureResponse, repo);
        uc.execute(new ReturnDroneInput(nonExistentDroneId));

        assertEquals(HttpResponseStatus.NOT_FOUND, lastResponse);
    }

    private void captureResponse(HttpResponseStatus response) {
        lastResponse = response;
    }

}
