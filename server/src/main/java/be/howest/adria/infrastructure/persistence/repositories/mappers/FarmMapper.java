package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.Farm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class FarmMapper implements ResultSetMapper<Farm> {

    private static final String FARM_ID = "farmId";
    private static final String FARM_NAME = "name";
    private static final String OWNER_ID = "ownerId";

    @Override
    public Farm map(ResultSet resultSet) throws SQLException {
        return makeFarm(resultSet);
    }

    private Farm makeFarm(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt(FARM_ID);
        final String name = resultSet.getString(FARM_NAME);
        final UUID userId = UUID.fromString(resultSet.getString(OWNER_ID));

        return new Farm(id, userId, name);
    }

}
