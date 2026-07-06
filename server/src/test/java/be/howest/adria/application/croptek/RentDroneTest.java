package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.rent_drone.RentDroneInput;
import be.howest.adria.application.croptek.rent_drone.RentDroneUC;
import be.howest.adria.domain.Drone;
import be.howest.adria.infrastructure.repositories.tables.TestDronesRepo;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RentDroneTest {

    private final DronesRepo repo = new TestDronesRepo();
    private final Drone drone1 = new Drone(UUID.randomUUID()); //Since we work with a set, I can't test it with more than one drone
    private final UUID randomUser = UUID.randomUUID();
    private HttpResponseStatus lastStatus;

    @BeforeEach
    void prepare(){
        repo.addDrone(drone1);
    }

    @Test
    void testChangeUserWhenBought(){

        RentDroneInput input = new RentDroneInput(randomUser);
        RentDroneUC uc = new RentDroneUC(this::evaluateBuyingDrones, repo);
        uc.execute(input);
    }

    private void evaluateBuyingDrones(HttpResponseStatus status){
        assertEquals(HttpResponseStatus.NO_CONTENT, status);
        assertTrue(repo.getAllDronesForUserId(randomUser).contains(drone1));
    }

    @Test
    void testChangeUserWhenDroneIsAvailable() {
        RentDroneInput input = new RentDroneInput(randomUser);
        RentDroneUC uc = new RentDroneUC(this::captureStatus, repo);

        uc.execute(input);

        assertEquals(HttpResponseStatus.NO_CONTENT, lastStatus);
        assertTrue(repo.getAllDronesForUserId(randomUser).contains(drone1));
    }

    @Test
    void testServiceUnavailableWhenNoDronesAvailable() {
        repo.clear();

        RentDroneInput input = new RentDroneInput(randomUser);
        RentDroneUC uc = new RentDroneUC(this::captureStatus, repo);

        uc.execute(input);

        assertEquals(HttpResponseStatus.SERVICE_UNAVAILABLE, lastStatus);
    }

    private void captureStatus(HttpResponseStatus status) {
        lastStatus = status;
    }

}
