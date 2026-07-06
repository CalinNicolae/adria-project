package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Farm;
import be.howest.adria.infrastructure.persistence.repositories.mappers.FarmMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SQLiteFarmsRepo implements FarmsRepo {

    private static final String QRY_SELECT_FARMS = "SELECT * FROM farms WHERE ownerId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_INSERT_FARM = "INSERT INTO farms(farmId, ownerId, name) VALUES(?, ?, ?)";
    private static final String QRY_SELECT_ALL_FARMS = "SELECT * FROM farms"; //NOSONAR: We need to use select statements

    private final FarmMapper mapper = new FarmMapper();

    @Override
    public Set<Farm> getAllFarms() throws RepoAccessException {
        final Set<Farm> farms = new HashSet<>();
        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_ALL_FARMS);
             final ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                farms.add(mapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving all farms data", e);
        }
        return farms;
    }

    @Override
    public Set<Farm> getFarms(UUID userAdriaId) throws RepoAccessException {
        final Set<Farm> farms = new HashSet<>();
        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_FARMS)) {

            statement.setString(1, userAdriaId.toString());

            try (final ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    final Farm farm = mapper.map(resultSet);
                    farms.add(farm);
                }
            }
        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving farms data", e);
        }
        return farms;
    }

    @Override
    public void addFarm(Farm farm) throws RepoAccessException {
        final int farmId = farm.getId();
        final String farmName = farm.getName();
        final UUID userAdriaId = farm.getUserId();

        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_INSERT_FARM)){
            statement.setInt(1, farmId);
            statement.setString(2, userAdriaId.toString());
            statement.setString(3, farmName);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

}
