package be.howest.adria.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DroneFailureTest {

    @Test
    void testCreateDroneFailure(){
        Drone drone = new Drone(UUID.randomUUID());

        DroneFailure droneFailure = new DroneFailure(drone, Instant.EPOCH);

        assertEquals(drone.getId(), droneFailure.getDroneId());
        assertEquals(Instant.EPOCH, droneFailure.getFailureTime());
    }

    @Test
    void testDroneFailureEquality(){
        Drone drone = new Drone(UUID.randomUUID());

        DroneFailure failure1 = new DroneFailure(drone, Instant.EPOCH);
        DroneFailure failure2 = new DroneFailure(drone, Instant.EPOCH);

        assertEquals(failure1, failure2);
    }

    @Test
    void testDroneFailureEqualityNotAlwaysTrue(){
        Drone drone = new Drone(UUID.randomUUID());

        DroneFailure failure1 = new DroneFailure(drone, Instant.EPOCH);
        DroneFailure failure2 = new DroneFailure(drone, Instant.now());

        assertNotEquals(failure1, failure2);
    }

    @Test
    void testEqualsWithNull() {
        DroneFailure droneFailure = new DroneFailure(UUID.randomUUID(), Instant.EPOCH);

        assertNotEquals(null, droneFailure);
    }

    @Test
    void testEqualsWithDifferentClass() {
        DroneFailure droneFailure = new DroneFailure(UUID.randomUUID(), Instant.EPOCH);

        String differentClassObject = "Not a DroneFailure";

        assertNotEquals(differentClassObject, droneFailure);
    }

    @Test
    void testHashCodeConsistency() {
        UUID droneId = UUID.randomUUID();
        Instant failureTime = Instant.EPOCH;

        DroneFailure failure1 = new DroneFailure(droneId, failureTime);
        DroneFailure failure2 = new DroneFailure(droneId, failureTime);

        assertEquals(failure1.hashCode(), failure2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentValues() {
        UUID droneId = UUID.randomUUID();

        DroneFailure failure1 = new DroneFailure(droneId, Instant.EPOCH);
        DroneFailure failure2 = new DroneFailure(droneId, Instant.now());

        assertNotEquals(failure1.hashCode(), failure2.hashCode());
    }

}
