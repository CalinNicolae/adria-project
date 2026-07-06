package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.FarmField;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmFieldMapper implements ResultSetMapper<FarmField> {

    private static final String FIELD_ID = "farmFieldId";
    private static final String FIELD_NAME = "name";
    private static final String FARM_ID = "farmId";

    @Override
    public FarmField map(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt(FIELD_ID);
        final int farmId = resultSet.getInt(FARM_ID);
        final String name = resultSet.getString(FIELD_NAME);

        return new FarmField(id, farmId, name);
    }

}
