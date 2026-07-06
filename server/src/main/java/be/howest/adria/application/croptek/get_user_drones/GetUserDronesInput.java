package be.howest.adria.application.croptek.get_user_drones;

import java.util.UUID;

public class GetUserDronesInput {

    private final UUID userId;

    public GetUserDronesInput(UUID userId){
        this.userId = userId;
    }

    public UUID getUserID(){
        return userId;
    }

}
