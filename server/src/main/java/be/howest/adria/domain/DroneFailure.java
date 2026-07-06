package be.howest.adria.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class DroneFailure {

    private final UUID droneId;
    private final Instant failureTime;

    public DroneFailure(UUID droneId, Instant failureTime) {
        this.droneId = Objects.requireNonNull(droneId);
        this.failureTime = Objects.requireNonNull(failureTime);
    }

    public DroneFailure(Drone drone, Instant failureTime){
        this(drone.getId(), failureTime);
    }

    public UUID getDroneId(){
        return droneId;
    }

    public Instant getFailureTime() {
        return failureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DroneFailure that = (DroneFailure) o;
        return droneId.equals(that.droneId) && failureTime.equals(that.failureTime);
    }

    @Override
    public int hashCode() {
        int result = droneId.hashCode();
        result = 31 * result + failureTime.hashCode();
        return result;
    }
}
