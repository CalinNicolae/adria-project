package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.PlantedCrop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

public class PlantedCropMapper implements ResultSetMapper<PlantedCrop> {

    private static final String CROP_ID = "cropId";
    private static final String HARVEST_DATE = "harvestDate";
    private static final String PLANTED_DATE = "plantedDate";
    private static final String TYPE_ID = "typeId";
    private static final String FARM_ID = "farmId";
    private static final String FARM_FIELD_ID = "farmFieldId";

    @Override
    public PlantedCrop map(ResultSet resultSet) throws SQLException {
        return getPlantedCrop(resultSet);
    }

    private PlantedCrop getPlantedCrop(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt(CROP_ID);
        final Optional<Instant> harvestDate = createOptionalInstantFromNullableTimestamp(resultSet.getTimestamp(HARVEST_DATE));
        final Instant plantedDate = resultSet.getTimestamp(PLANTED_DATE).toInstant();
        final int typeId = resultSet.getInt(TYPE_ID);
        final int farmId = resultSet.getInt(FARM_ID);
        final int farmFieldId = resultSet.getInt(FARM_FIELD_ID);

        return new PlantedCrop(id, typeId, farmId, farmFieldId, plantedDate, harvestDate);
    }

    private Optional<Instant> createOptionalInstantFromNullableTimestamp(java.sql.Timestamp timestamp) {
        if(timestamp == null) {
            return Optional.empty();
        }else{
            return Optional.of(timestamp.toInstant());
        }
    }

}
