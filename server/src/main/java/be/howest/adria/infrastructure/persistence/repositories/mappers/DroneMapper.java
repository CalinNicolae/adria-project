package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.Drone;
import be.howest.adria.domain.DroneActivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class DroneMapper implements ResultSetMapper<Drone> {
    private static final String DRONE_ID = "droneId";
    private static final String MANUFACTURING_DATE = "manufacturingDate";
    private static final String FUNCTIONAL = "isFunctional";
    private static final String NEEDS_REPAIR = "needsRepair";
    private static final String BATTERY_LEVEL = "batteryLevel";
    private static final String CURRENT_ACTIVITY = "currentActivity";
    private static final String USER_ID = "userId";

    @Override
    public Drone map(ResultSet resultSet) throws SQLException {
        return makeDrone(resultSet);
    }

    private Drone makeDrone(ResultSet resultSet) throws SQLException {
        final UUID id = UUID.fromString(resultSet.getString(DRONE_ID));
        final LocalDate manufacturingDate = resultSet.getDate(MANUFACTURING_DATE).toLocalDate();
        final boolean functional = resultSet.getBoolean(FUNCTIONAL);
        final boolean needsRepair = resultSet.getBoolean(NEEDS_REPAIR);
        final int batteryLevel = resultSet.getInt(BATTERY_LEVEL);
        final DroneActivity currentActivity = DroneActivity.valueOf(resultSet.getString(CURRENT_ACTIVITY));

        final String userIdStr = resultSet.getString(USER_ID);

        Optional<UUID> userId;
        if(userIdStr == null) {
            userId = Optional.empty();
        }else{
            userId = Optional.of(UUID.fromString(userIdStr));
        }

        return new Drone(id, manufacturingDate, functional, needsRepair, batteryLevel, currentActivity, userId);
    }

}
