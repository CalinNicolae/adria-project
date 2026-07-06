package be.howest.adria.application.croptek.get_user_notifications;

import be.howest.adria.domain.Notification;

import java.util.ArrayList;
import java.util.List;

public class GetUserNotificationsOutput extends ArrayList<Notification> {

    public GetUserNotificationsOutput(List<Notification> output) {
        super(output);
    }

}
