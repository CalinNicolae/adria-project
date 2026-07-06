package be.howest.adria.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DroneTest {
    @Test
    void testCreateDrone(){

        UUID droneId = UUID.randomUUID();

        Drone drone = new Drone(droneId);

        assertEquals(droneId, drone.getId());
        assertEquals(DroneActivity.PASSIVE, drone.getActivity());
        assertEquals(LocalDate.now(), drone.getManufacturingDate());
        assertEquals(100, drone.getBatteryLevel());
        assertTrue(drone.isFunctional());
        assertFalse(drone.needsRepair());
        assertEquals(Optional.empty(), drone.getUserId());
    }

    @Test
    void testSetUser(){
        Drone drone = new Drone(UUID.randomUUID());
        UUID adriaId = UUID.fromString("50b1871f-e600-4791-9566-787b029854b7");

        drone.setUserId(Optional.of(adriaId));

        assertTrue(drone.getUserId().isPresent());
        assertEquals(adriaId, drone.getUserId().get());
    }

    @Test
    void testSetActivity(){
        Drone drone = new Drone(UUID.randomUUID());

        drone.setActivity(DroneActivity.WATERING);

        assertEquals(DroneActivity.WATERING, drone.getActivity());
    }

    @Test
    void testSetBatteryLevel(){
        Drone drone = new Drone(UUID.randomUUID());

        drone.setBatteryLevel(0);

        assertEquals(0, drone.getBatteryLevel());
    }

    @Test
    void testSetFunctionality(){
        Drone drone = new Drone(UUID.randomUUID());

        drone.setFunctional(false);

        assertFalse(drone.isFunctional());
    }

    @Test
    void testSetRepairNeed(){
        Drone drone  = new Drone(UUID.randomUUID());

        drone.setNeedsRepair(true);

        assertTrue(drone.needsRepair());
    }

    @Test
    void testEquality(){
        UUID droneId =  UUID.randomUUID();

        Drone drone1 = new Drone(droneId);
        Drone drone2 = new Drone(droneId);

        assertEquals(drone1, drone2);
    }

    @Test
    void testEqualityIsNotAlwaysTrue(){
        UUID droneId1 = UUID.fromString("50b1871f-e600-4791-9566-787b029854b7");
        UUID droneId2 = UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b2");

        Drone drone1 = new Drone(droneId1);
        Drone drone2 = new Drone(droneId2);

        assertNotEquals(drone1, drone2);
    }
    @Test
    void testConstructorWithUser() {
        UUID droneId = UUID.randomUUID();
        LocalDate manufacturingDate = LocalDate.of(2020, 1, 1);
        boolean functional = true;
        boolean needsRepair = false;
        int batteryLevel = 80;
        DroneActivity activity = DroneActivity.CHARGING;

        User user = new User(UUID.randomUUID(), "John Doe");

        Drone drone = new Drone(droneId, manufacturingDate, functional, needsRepair, batteryLevel, activity, user);

        assertTrue(drone.getUserId().isPresent());
        assertEquals(user.getAdriaId(), drone.getUserId().get());
    }

    @Test
    void testEqualsWithNull() {
        UUID droneId = UUID.randomUUID();
        Drone drone = new Drone(droneId);

        assertNotEquals(null, drone);
    }

    @Test
    void testEqualsWithDifferentClass() {
        UUID droneId = UUID.randomUUID();
        Drone drone = new Drone(droneId);

        String differentClassObject = "Not a Drone";
        assertNotEquals(differentClassObject, drone);
    }
}
