package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Notification;
import be.howest.adria.infrastructure.persistence.repositories.mappers.NotificationMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SQLiteNotificationsRepo implements NotificationsRepo {

    private static final String QRY_SELECT_NOTIFICATIONS_BY_USER_ID = "SELECT * FROM notifications WHERE userId = ?"; //NOSONAR: We have to use select
    private static final String QRY_INSERT_NOTIFICATION = "INSERT INTO notifications(notificationId, userId, title, message, read) VALUES(?, ?, ?, ?, ?)";
    private static final String QRY_SELECT_NOTIFICATION_BY_NOTIFICATION_ID = "SELECT * FROM notifications WHERE notificationId = ?"; //NOSONAR We have to use select
    private static final String QRY_DELETE_NOTIFICATION = "DELETE FROM notifications WHERE notificationId = ?";

    private static final NotificationMapper mapper = new NotificationMapper();

    @Override
    public List<Notification> getNotifications(UUID userAdriaId) throws RepoAccessException {
        List<Notification> notifications = new ArrayList<>();

        try(final Connection connection = JdbcConnection.instance().getConnection();
			final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_NOTIFICATIONS_BY_USER_ID)){
            statement.setString(1, userAdriaId.toString());

            final ResultSet rs = statement.executeQuery();

            while (rs.next()){
                notifications.add(mapper.map(rs));
            }

            return notifications;
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

    @Override
    public void addNotification(Notification notification) throws RepoAccessException {
        try(Connection connection = JdbcConnection.instance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QRY_INSERT_NOTIFICATION)){
            statement.setInt(1, notification.getId());
            statement.setString(2, notification.getUserId().toString());
            statement.setString(3, notification.getTitle());
            statement.setString(4, notification.getMessage());
            statement.setBoolean(5, notification.isRead());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

    @Override
    public Optional<Notification> getNotificationById(int notificationId) throws RepoAccessException {
        try(Connection connection = JdbcConnection.instance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QRY_SELECT_NOTIFICATION_BY_NOTIFICATION_ID)){
            statement.setInt(1, notificationId);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return Optional.of(mapper.map(rs));
            }
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }

        return Optional.empty();
    }

    @Override
    public void update(Notification notification) {
        deleteNotification(notification);
        addNotification(notification);
    }

    private void deleteNotification(Notification notification){
        try(Connection connection = JdbcConnection.instance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QRY_DELETE_NOTIFICATION)){
            statement.setInt(1, notification.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

}
