package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.CropType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CropTypeMapper implements ResultSetMapper<CropType> {

    private static final String TYPE_ID = "typeId";
    private static final String TYPE_NAME = "name";
    private static final String MIN_GROWTH_DAYS = "minGrowthDays";
    private static final String MAX_GROWTH_DAYS = "maxGrowthDays";

    @Override
    public CropType map(ResultSet resultSet) throws SQLException {
        return getCropType(resultSet);
    }

    private CropType getCropType(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt(TYPE_ID);
        final String name = resultSet.getString(TYPE_NAME);
        final int minGrowthDays = resultSet.getInt(MIN_GROWTH_DAYS);
        final int maxGrowthDays = resultSet.getInt(MAX_GROWTH_DAYS);

        return new CropType(id, name, minGrowthDays, maxGrowthDays);
    }

}
