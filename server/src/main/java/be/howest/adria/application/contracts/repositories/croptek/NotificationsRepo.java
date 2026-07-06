package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Notification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationsRepo {

    List<Notification> getNotifications(UUID userAdriaId) throws RepoAccessException;

    void addNotification(Notification notification) throws RepoAccessException;

	Optional<Notification> getNotificationById(int notificationId) throws RepoAccessException;

	void update(Notification notification);

}
