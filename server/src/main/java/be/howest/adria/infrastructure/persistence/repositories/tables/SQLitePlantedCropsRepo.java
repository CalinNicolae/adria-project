package be.howest.adria.infrastructure.persistence.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.PlantedCrop;
import be.howest.adria.infrastructure.persistence.repositories.mappers.PlantedCropMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SQLitePlantedCropsRepo implements PlantedCropsRepo {

    private static final String QRY_SELECT_PLANTED_CROPS = "SELECT * FROM plantedCrops WHERE farmId  = ? AND farmFieldId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_SELECT_PLANTED_CROPS_BY_FARMID = "SELECT * FROM plantedCrops WHERE farmId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_INSERT_PLANTED_CROP = "INSERT INTO plantedCrops(farmId, farmFieldId, cropId, typeId, harvestDate, plantedDate) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String QRY_SELECT_ALL_PLANTED_CROPS = "SELECT * FROM plantedCrops"; //NOSONAR: We need to use select statements

    private final PlantedCropMapper mapper = new PlantedCropMapper();

    @Override
    public Set<PlantedCrop> getPlantedCrops(int farmId, int farmFieldId) throws RepoAccessException {
        Set<PlantedCrop> plantedCrops = new HashSet<>();

        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_PLANTED_CROPS)) {

            statement.setInt(1, farmId);
            statement.setInt(2, farmFieldId);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PlantedCrop plantedCrop = mapper.map(resultSet);
                plantedCrops.add(plantedCrop);
            }
        } catch (SQLException e) {
            throw new RepoAccessException("Failed to retrieve planted crops for farmId: " + farmId  + " an farmFieldId: " + farmFieldId, e);
        }

        return plantedCrops;
    }

    @Override
    public Set<PlantedCrop> getPlantedCrops(int farmId) {
        Set<PlantedCrop> plantedCrops = new HashSet<>();
        try(Connection conn = JdbcConnection.instance().getConnection();
            PreparedStatement statement = conn.prepareStatement(QRY_SELECT_PLANTED_CROPS_BY_FARMID)){
            statement.setInt(1, farmId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                plantedCrops.add(mapper.map(rs));
            }

            return plantedCrops;
        } catch (SQLException e) {
            throw new RepoAccessException("error finding planted crops on farm with id " + farmId, e);
        }
    }

    @Override
    public Set<PlantedCrop> getAllPlantedCrops() {
        Set<PlantedCrop> plantedCrops = new HashSet<>();

        try(Connection conn = JdbcConnection.instance().getConnection();
            PreparedStatement statement = conn.prepareStatement(QRY_SELECT_ALL_PLANTED_CROPS);
            ResultSet rs = statement.executeQuery()){
            while(rs.next()){
                plantedCrops.add(mapper.map(rs));
            }

            return plantedCrops;
        } catch (SQLException e) {
                throw new RepoAccessException("Error getting all planted crops", e);
        }
    }

    @Override
    public void addPlantedCrop(PlantedCrop plantedCrop) throws RepoAccessException {
        final int cropId = plantedCrop.getId();
        final Optional<Instant> harvestDate = plantedCrop.getHarvest();
        final Instant plantedDate = plantedCrop.getPlanted();
        final int typeId = plantedCrop.getTypeId();
        final int farmId = plantedCrop.getFarmId();
        final int fieldId = plantedCrop.getFarmFieldId();

        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_INSERT_PLANTED_CROP)){
            statement.setInt(1, farmId);
            statement.setInt(2, fieldId);
            statement.setInt(3, cropId);
            statement.setInt(4, typeId);

            if(harvestDate.isPresent()) {
                statement.setTimestamp(5, Timestamp.from(harvestDate.get()));
            }else{
                statement.setNull(5, java.sql.Types.TIMESTAMP);
            }
            statement.setTimestamp(6, Timestamp.from(plantedDate));

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

}
