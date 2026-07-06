package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Drone;
import be.howest.adria.domain.DroneActivity;
import be.howest.adria.infrastructure.persistence.repositories.mappers.DroneMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SQLiteDronesRepo implements DronesRepo {

    private static final String QRY_SELECT_DRONES = "SELECT * FROM drones"; //NOSONAR: We need to use select statements
    private static final String QRY_SELECT_AVAILABLE_DRONE = "SELECT * FROM drones WHERE userId IS NULL LIMIT 1"; //NOSONAR: We need to use select statements
    private static final String QRY_SELECT_DRONE_BY_DRONE_ID = "SELECT * FROM drones WHERE droneId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_SELECT_DRONES_BY_USER_ID = "SELECT * FROM drones WHERE userId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_INSERT_DRONE = "INSERT INTO drones(droneId, userId, manufacturingDate, isFunctional, needsRepair, batteryLevel, currentActivity) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String QRY_DELETE_DRONE = "DELETE FROM drones WHERE droneId = ?";
    private static final String QRY_CLEAR_DRONES = "DELETE FROM drones";

    private final DroneMapper mapper = new DroneMapper();

    @Override
    public void addDrone(Drone drone) throws RepoAccessException {
        final UUID droneId = drone.getId();
        final LocalDate manufacturingDate = drone.getManufacturingDate();
        final boolean functional = drone.isFunctional();
        final boolean needsRepair = drone.needsRepair();
        final int batteryLevel = drone.getBatteryLevel();
        final DroneActivity currentActivity = drone.getActivity();
        final Optional<UUID> userId = drone.getUserId();

        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_INSERT_DRONE)) {

            statement.setString(1, droneId.toString());

            if (userId.isPresent()) {
                statement.setString(2, userId.get().toString());
            } else {
                statement.setNull(2, java.sql.Types.VARCHAR);
            }

            statement.setDate(3, Date.valueOf(manufacturingDate));
            statement.setBoolean(4, functional);
            statement.setBoolean(5, needsRepair);
            statement.setInt(6, batteryLevel);
            statement.setString(7, currentActivity.name());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

    @Override
    public Set<Drone> getAllDrones() throws RepoAccessException {
        Set<Drone> drones = new HashSet<>();

        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_DRONES);
             final ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Drone drone = mapper.map(resultSet);
                drones.add(drone);
            }

        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving all drones", e);
        }

        return drones;
    }

    @Override
    public Set<Drone> getAllDronesForUserId(UUID userId) {
        Set<Drone> drones = new HashSet<>();

        try (Connection connection = JdbcConnection.instance().getConnection();
             PreparedStatement statement = connection.prepareStatement(QRY_SELECT_DRONES_BY_USER_ID)) {
            statement.setString(1, userId.toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                drones.add(mapper.map(resultSet));
            }

        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving drones", e);
        }

        return drones;
    }

    @Override
    public Optional<Drone> getDrone(UUID id) throws RepoAccessException {
        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_DRONE_BY_DRONE_ID)) {

            statement.setString(1, id.toString());

            try(final ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return Optional.of(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving drone data", e);
        }

        return Optional.empty();
    }

    @Override
    public void update(Drone drone) throws RepoAccessException {
        deleteDrone(drone.getId());
        addDrone(drone);
    }

    private void deleteDrone(UUID droneId) throws RepoAccessException {
        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_DELETE_DRONE)) {

            statement.setString(1, droneId.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

    @Override
    public Optional<Drone> getAvailableDrones() {
        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_AVAILABLE_DRONE);
             final ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return Optional.of(mapper.map(resultSet));
            }

        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving available drone", e);
        }

        return Optional.empty();
    }

    @Override
    public void clear(){
        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_CLEAR_DRONES)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RepoAccessException("Error clearing drones table", e);
        }
    }
}
