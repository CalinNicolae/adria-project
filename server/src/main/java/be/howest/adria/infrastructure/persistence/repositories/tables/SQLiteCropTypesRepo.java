package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.CropType;
import be.howest.adria.infrastructure.persistence.repositories.mappers.CropTypeMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class SQLiteCropTypesRepo implements CropTypesRepo {

    private static final String QRY_SELECT_CROP_TYPE_BY_ID = "SELECT * FROM cropTypes WHERE typeId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_INSERT_CROP_TYPE = "INSERT INTO cropTypes(typeId, name, minGrowthDays, maxGrowthDays) VALUES(?, ?, ?, ?)";
    private static final String QRY_SELECT_ALL_CROP_TYPES = "SELECT * FROM cropTypes"; //NOSONAR: We need to use select statements

    private final CropTypeMapper mapper = new CropTypeMapper();

    @Override
    public CropType getCropType(int id) throws RepoAccessException {
        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_CROP_TYPE_BY_ID)) {

            statement.setInt(1, id);

            try (final ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.map(resultSet);
                } else {
                    throw new RepoAccessException("CropType not found with id: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving CropType with id: " + id, e);
        }
    }

    @Override
    public Set<CropType> getAllCropTypes() {
        Set<CropType> cropTypes = new HashSet<>();

        try(Connection conn = JdbcConnection.instance().getConnection();
            PreparedStatement statement = conn.prepareStatement(QRY_SELECT_ALL_CROP_TYPES);
            ResultSet rs = statement.executeQuery()){
            while (rs.next()){
                cropTypes.add(mapper.map(rs));
            }

            return cropTypes;
        } catch (SQLException e) {
            throw new RepoAccessException("Error retrieving all cropTypes", e);
        }
    }

    @Override
    public void addCropType(CropType cropType) throws RepoAccessException {
        final int typeId = cropType.getId();
        final String name = cropType.getName();
        final int minGrowthDays = cropType.getMinGrowthDays();
        final int maxGrowthDays = cropType.getMaxGrowthDays();

        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement selectStatement = connection.prepareStatement(QRY_SELECT_CROP_TYPE_BY_ID)){
            selectStatement.setInt(1, typeId);
            final ResultSet resultSet = selectStatement.executeQuery();

            if(!resultSet.next()){
                try(PreparedStatement statement = connection.prepareStatement(QRY_INSERT_CROP_TYPE)){
                    statement.setInt(1, typeId);
                    statement.setString(2, name);
                    statement.setInt(3, minGrowthDays);
                    statement.setInt(4, maxGrowthDays);

                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

}
