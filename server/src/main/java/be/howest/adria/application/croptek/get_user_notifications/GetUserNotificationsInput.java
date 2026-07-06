package be.howest.adria.application.croptek.get_user_notifications;

import java.util.UUID;

public class GetUserNotificationsInput {

    private final UUID userAdriaId;

    public GetUserNotificationsInput(UUID userAdriaId) {
        this.userAdriaId = userAdriaId;
    }

    public UUID getUserAdriaId() {
        return userAdriaId;
    }

}
