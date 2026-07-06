package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.FarmFieldsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.FarmField;
import be.howest.adria.infrastructure.persistence.repositories.mappers.FarmFieldMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SQLiteFarmFieldsRepo implements FarmFieldsRepo {

    private static final String QRY_SELECT_FARM_FIELDS = "SELECT * FROM farmFields WHERE farmId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_INSERT_FARM_FIELD = "INSERT INTO farmFields(farmId, farmFieldId, name) VALUES(?,?,?)";
    private static final String QRY_SELECT_ALL_FARM_FIELDS = "SELECT * FROM farmFields"; //NOSONAR: We need to use select statements

    private final FarmFieldMapper mapper = new FarmFieldMapper();

    @Override
    public Set<FarmField> getFarmFields(int farmId) throws RepoAccessException {
        Set<FarmField> farmFields = new HashSet<>();

        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_FARM_FIELDS)) {

            statement.setInt(1, farmId);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FarmField farmField = mapper.map(resultSet);
                farmFields.add(farmField);
            }
        } catch (SQLException e) {
            throw new RepoAccessException("Failed to retrieve farm fields for farmId: " + farmId, e);
        }

        return farmFields;
    }

    @Override
    public Set<FarmField> getAllFarmFields() {
        Set<FarmField> farmFields = new HashSet<>();
        try(Connection conn = JdbcConnection.instance().getConnection();
            PreparedStatement statement = conn.prepareStatement(QRY_SELECT_ALL_FARM_FIELDS);
            ResultSet rs = statement.executeQuery()){
            while(rs.next()){
                farmFields.add(mapper.map(rs));
            }

            return farmFields;
        } catch (SQLException e) {
            throw new RepoAccessException("Error getting all farmfields", e);
        }
    }

    @Override
    public void addFarmField(FarmField farmField) throws RepoAccessException {
        final int fieldId = farmField.getId();
        final String fieldName = farmField.getName();
        final int farmId = farmField.getFarmId();

        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_INSERT_FARM_FIELD)) {
            statement.setInt(1, farmId);
            statement.setInt(2, fieldId);
            statement.setString(3, fieldName);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

}
