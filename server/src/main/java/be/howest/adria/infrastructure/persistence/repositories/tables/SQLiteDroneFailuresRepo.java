package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.DroneFailuresRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.DroneFailure;
import be.howest.adria.infrastructure.persistence.repositories.mappers.DroneFailureMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLiteDroneFailuresRepo implements DroneFailuresRepo {

    private static final String QRY_SELECT_FAILURES_BY_DRONE_ID = "SELECT * FROM failures WHERE droneId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_INSERT_FAILURE = "INSERT INTO failures(droneId, failureTime) VALUES(?, ?)";
    private static final String QRY_SELECT_ALL_FAILURES = "SELECT * FROM failures"; //NOSONAR: We need to use select statements

    private final DroneFailureMapper mapper = new DroneFailureMapper();

    @Override
    public List<DroneFailure> getDroneFailures(UUID droneId) throws RepoAccessException {
        List<DroneFailure> failures = new ArrayList<>();

       try(final Connection connection = JdbcConnection.instance().getConnection();
           final  PreparedStatement statement = connection.prepareStatement(QRY_SELECT_FAILURES_BY_DRONE_ID)){
           statement.setString(1, droneId.toString());
           ResultSet resultSet = statement.executeQuery();

           while (resultSet.next()){
               failures.add(mapper.map(resultSet));
           }

           return failures;
       }
       catch (final SQLException e){
           throw new RepoAccessException(e);
       }
    }

    @Override
    public List<DroneFailure> getAllDroneFailures() {
        try(Connection conn = JdbcConnection.instance().getConnection();
            PreparedStatement statement = conn.prepareStatement(QRY_SELECT_ALL_FAILURES);
            ResultSet rs = statement.executeQuery()) {
            List<DroneFailure> droneFailures = new ArrayList<>();

            while(rs.next()){
                droneFailures.add(mapper.map(rs));
            }

            return droneFailures;
        } catch (SQLException e) {
            throw new RepoAccessException("Error getting all drones", e);
        }
    }

    @Override
    public void addFailure(DroneFailure failure) throws RepoAccessException {
        final Instant failureTime = failure.getFailureTime();

        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_INSERT_FAILURE)){
            statement.setString(1, failure.getDroneId().toString());
            statement.setTimestamp(2, Timestamp.from(failureTime));

            statement.executeUpdate();
        }catch(final SQLException e) {
            throw new RepoAccessException(e);
        }
    }

}
