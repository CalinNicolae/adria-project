package be.howest.adria.application.croptek.set_notification_read;

public class SetNotificationReadInput {

    private final int notificationId;
    private final boolean read;

    public SetNotificationReadInput(int notificationId, boolean read) {
        this.notificationId = notificationId;
        this.read = read;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public boolean isRead() {
        return read;
    }

}
