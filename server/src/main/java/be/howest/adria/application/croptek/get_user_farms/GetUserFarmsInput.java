package be.howest.adria.application.croptek.get_user_farms;

import java.util.UUID;

public class GetUserFarmsInput {

    private final UUID userAdriaId;

    public GetUserFarmsInput(UUID userAdriaId) {
        this.userAdriaId = userAdriaId;
    }

    public UUID getUserAdriaId() {
        return userAdriaId;
    }

}
