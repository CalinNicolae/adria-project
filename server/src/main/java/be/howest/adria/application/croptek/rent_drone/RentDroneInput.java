package be.howest.adria.application.croptek.rent_drone;

import java.util.UUID;

public class RentDroneInput {
    private final UUID adriaId;

    public RentDroneInput(UUID adriaId) {
        this.adriaId = adriaId;
    }

    public UUID getAdriaId() {
        return adriaId;
    }
}