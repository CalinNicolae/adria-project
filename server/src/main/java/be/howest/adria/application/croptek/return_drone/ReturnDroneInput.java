package be.howest.adria.application.croptek.return_drone;

import java.util.UUID;

public class ReturnDroneInput {
    private final UUID droneId;

    public ReturnDroneInput(UUID droneId) {
        this.droneId = droneId;
    }

    public UUID getDroneId() {
        return droneId;
    }

}