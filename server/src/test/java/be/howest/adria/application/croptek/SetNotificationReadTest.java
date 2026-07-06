package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.croptek.set_notification_read.SetNotificationReadInput;
import be.howest.adria.application.croptek.set_notification_read.SetNotificationReadUC;
import be.howest.adria.domain.Notification;
import be.howest.adria.infrastructure.repositories.tables.TestNotificationsRepo;
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SetNotificationReadTest {

    private final NotificationsRepo repo = new TestNotificationsRepo();

    private final int notificationId1 = 15;
    private final int notificationId2 = 22;

    private final Notification notification1 = new Notification(
            notificationId1,
            UUID.randomUUID(),
            "Goose",
            "Goose stuck in farm",
            true);

    private final Notification notification2 = new Notification(
            notificationId2,
            UUID.randomUUID(),
            "Crop mutated",
            "Crop became big",
            false);

    @BeforeEach
    void prepare(){
        repo.addNotification(notification1);
        repo.addNotification(notification2);
    }

    @Test
    void testSetNotificationUnread(){
        final SetNotificationReadUC uc = new SetNotificationReadUC(this::setNotificationUnread_checkResults, repo);
        uc.execute(new SetNotificationReadInput(notificationId1, false));
    }

    private void setNotificationUnread_checkResults(HttpResponseStatus result) {
        assertEquals(HttpResponseStatus.NO_CONTENT, result);
        assertTrue(repo.getNotificationById(notificationId1).isPresent());
        assertFalse(repo.getNotificationById(notificationId1).get().isRead());
    }

    @Test
    void testSetNotificationRead(){
        final SetNotificationReadUC uc = new SetNotificationReadUC(this::setNotificationRead_checkResults, repo);
        uc.execute(new SetNotificationReadInput(notificationId2, true));
    }

    private void setNotificationRead_checkResults(HttpResponseStatus result) {
        assertEquals(HttpResponseStatus.NO_CONTENT, result);
        assertTrue(repo.getNotificationById(notificationId2).isPresent());
    }

    @Test
    void testNotificationNotFound() {
        final int nonExistentNotificationId = 999; // A notification ID that doesn't exist in the repo
        final SetNotificationReadUC uc = new SetNotificationReadUC(this::notificationNotFound_checkResults, repo);
        uc.execute(new SetNotificationReadInput(nonExistentNotificationId, true));
    }

    private void notificationNotFound_checkResults(HttpResponseStatus result) {
        assertEquals(HttpResponseStatus.NOT_FOUND, result);
    }

}
