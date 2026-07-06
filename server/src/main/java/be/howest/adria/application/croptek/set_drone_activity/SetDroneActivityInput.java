package be.howest.adria.application.croptek.set_drone_activity;

import be.howest.adria.domain.DroneActivity;

import java.util.Objects;
import java.util.UUID;

public class SetDroneActivityInput {

    private final UUID droneId;
    private final DroneActivity activity;

    public SetDroneActivityInput(UUID droneId, DroneActivity activity) {
      this.droneId = Objects.requireNonNull(droneId);
        this.activity = Objects.requireNonNull(activity);
    }

    public UUID getDroneId() {
        return droneId;
    }

    public DroneActivity getActivity() {
        return activity;
    }
}
