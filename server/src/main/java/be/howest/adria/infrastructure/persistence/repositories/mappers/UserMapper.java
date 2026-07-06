package be.howest.adria.infrastructure.persistence.repositories.mappers;

import be.howest.adria.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserMapper implements ResultSetMapper<User>{

    private static final String ADRIA_ID = "users.adriaId";
    private static final String FULL_NAME = "users.fullName";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        return makeUser(resultSet);
    }

    private User makeUser(ResultSet resultSet) throws SQLException {
        final UUID id = UUID.fromString(resultSet.getString(ADRIA_ID));
        final String fullName = resultSet.getString(FULL_NAME);

        return new User(id, fullName);
    }

}
