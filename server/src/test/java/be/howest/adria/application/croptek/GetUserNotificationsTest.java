package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.croptek.get_user_notifications.GetUserNotificationsInput;
import be.howest.adria.application.croptek.get_user_notifications.GetUserNotificationsOutput;
import be.howest.adria.application.croptek.get_user_notifications.GetUserNotificationsUC;
import be.howest.adria.domain.Notification;
import be.howest.adria.infrastructure.repositories.tables.TestNotificationsRepo;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetUserNotificationsTest {

	private final NotificationsRepo notificationsRepo = new TestNotificationsRepo();

	private final UUID userId1 = UUID.randomUUID();
	private final UUID userId2 = UUID.randomUUID();

	private final Notification notification1 = new Notification(1, userId1,
			"Drone offline", "Drone got eaten", true);
	private final Notification notification2 = new Notification(2, userId2,
			"Farm flooded", "Lots of rain caused flooding", true);
	private final Notification notification3 = new Notification(3, userId2,
			"Drone crashed", "Segmentation fault (core dumped)", false);

	@BeforeEach
	void prepare() {
		notificationsRepo.addNotification(notification1);
		notificationsRepo.addNotification(notification2);
		notificationsRepo.addNotification(notification3);
	}

	@Test
	void getUserNotifications() {
		final GetUserNotificationsUC uc = new GetUserNotificationsUC(this::evaluate_getUserNotifications, notificationsRepo);
		uc.execute(new GetUserNotificationsInput(userId2));
	}

	private void evaluate_getUserNotifications(GetUserNotificationsOutput data) {
		Assertions.assertTrue(!data.contains(notification1) && data.contains(notification2) && data.contains(notification3));
	}

}
