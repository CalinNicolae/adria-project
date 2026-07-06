package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.DroneFailure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

public class DroneFailureMapper implements ResultSetMapper<DroneFailure> {

    private static final String DRONE_ID = "droneId";
    private static final String FAILURE_TIME = "failureTime";

    @Override
    public DroneFailure map(ResultSet resultSet) throws SQLException {
        final UUID droneId = UUID.fromString(resultSet.getString(DRONE_ID));
        final Instant failureTime = resultSet.getTimestamp(FAILURE_TIME).toInstant();

        return new DroneFailure(droneId, failureTime);
    }
}
