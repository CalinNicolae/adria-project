package be.howest.adria.domain;

import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationTest {

	@Test
	void testCreateNotification() {

		final int notificationId = 5;
		final UUID userId = UUID.randomUUID();
		final String title = "Drone damaged";
		final String message = "test";
		final boolean read = true;

		final Notification notification = new Notification(
				notificationId, userId, title, message, read);

		assertEquals(notificationId, notification.getId());
		assertEquals(userId, notification.getUserId());
		assertEquals(title, notification.getTitle());
		assertEquals(message, notification.getMessage());
		assertEquals(read, notification.isRead());

	}

	@Test
	void testEquality() {

		final Notification notification1 = new Notification(9, UUID.randomUUID(),
				"Drone stuck", "Got stuck in a tree", true);
		final Notification notification2 = new Notification(9, UUID.randomUUID(),
				"Drone died", "Battery low", false);
		final Notification notification3 = new Notification(10, UUID.randomUUID(),
				"Farm profit increased by 15%", "Big profit", true);

		assertEquals(notification1, notification2);
		assertNotEquals(notification2, notification3);

	}


	@Test
	void testGetters() {

		final UUID userId = UUID.randomUUID();

		final Notification notification = new Notification(5, userId,
				"Fire department called", "Crops caught fire", true);

		assertEquals(5, notification.getId());
		assertEquals(userId, notification.getUserId());
		assertEquals("Fire department called", notification.getTitle());
		assertEquals("Crops caught fire", notification.getMessage());
		assertTrue(notification.isRead());

	}

	@Test
	void testSetters() {

		final UUID userId = UUID.randomUUID();

		final Notification notification = new Notification(6, userId,
				"Police called", "Crops stolen", false);

		assertFalse(notification.isRead());

		notification.setRead(true);

		assertTrue(notification.isRead());
	}

	@Test
	void testEqualsWithNull() {
		final Notification notification = new Notification(1, UUID.randomUUID(),
				"Title", "Message", false);

		assertNotEquals(null, notification);
	}

	@Test
	void testEqualsWithDifferentClass() {
		final Notification notification = new Notification(1, UUID.randomUUID(),
				"Title", "Message", false);

		String differentClassObject = "Not a Notification";

		assertNotEquals(differentClassObject, notification);
	}

	@Test
	void testHashCodeWithSameId() {
		final Notification notification1 = new Notification(1, UUID.randomUUID(),
				"Title1", "Message1", false);
		final Notification notification2 = new Notification(1, UUID.randomUUID(),
				"Title2", "Message2", true);

		assertEquals(notification1.hashCode(), notification2.hashCode());
	}

	@Test
	void testHashCodeWithDifferentIds() {
		final Notification notification1 = new Notification(1, UUID.randomUUID(),
				"Title1", "Message1", false);
		final Notification notification2 = new Notification(2, UUID.randomUUID(),
				"Title2", "Message2", true);

		assertNotEquals(notification1.hashCode(), notification2.hashCode());
	}
}
