package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.Notification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class NotificationMapper implements ResultSetMapper<Notification>{

    @Override
    public Notification map(ResultSet resultSet) throws SQLException {
        return makeNotification(resultSet);
    }

    private Notification makeNotification(ResultSet resultSet) throws SQLException {
        final int notificationId = resultSet.getInt("notificationId");
        final UUID userId = UUID.fromString(resultSet.getString("userId"));
        final String title = resultSet.getString("title");
        final String message = resultSet.getString("message");
        final boolean read = resultSet.getBoolean("read");

        return new Notification(notificationId, userId, title, message, read);
    }

}
