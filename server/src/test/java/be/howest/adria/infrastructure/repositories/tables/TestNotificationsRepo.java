package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestNotificationsRepo implements NotificationsRepo {

	private final List<Notification> notifications = new ArrayList<>();

	@Override
	public List<Notification> getNotifications(UUID userAdriaId) throws RepoAccessException {
		final List<Notification> notificationsFound = new ArrayList<>();

		for (Notification notification : notifications) {
			if(notification.getUserId().equals(userAdriaId)) {
				notificationsFound.add(notification);
			}
		}

		return notificationsFound;
	}

	@Override
	public void addNotification(Notification notification) throws RepoAccessException {
		notifications.add(notification);
	}

	@Override
	public Optional<Notification> getNotificationById(int notificationId) throws RepoAccessException {

		for (Notification notification : notifications) {
			if(notification.getId() == notificationId) {
				return Optional.of(notification);
			}
		}

		return Optional.empty();
	}

	@Override
	public void update(Notification notification) {
		notifications.remove(notification);
		notifications.add(notification);
	}

}
