package be.howest.adria.infrastructure.persistence.repositories.tables;


import be.howest.adria.application.contracts.repositories.croptek.UsersRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.User;
import be.howest.adria.infrastructure.persistence.repositories.mappers.UserMapper;
import be.howest.adria.infrastructure.persistence.shared.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SQLiteUsersRepo implements UsersRepo {

    private static final String QRY_SELECT_ALL_USERS = "SELECT * FROM users"; //NOSONAR: We need to use select statements
    private static final String QRY_SELECT_USER = "SELECT * FROM users WHERE adriaId = ?"; //NOSONAR: We need to use select statements
    private static final String QRY_INSERT_USER = "INSERT INTO users(adriaId, fullName) VALUES(?, ?)";

    private final UserMapper userMapper = new UserMapper();

    @Override
    public User getUser(UUID userAdriaId) throws RepoAccessException {
        try (final Connection connection = JdbcConnection.instance().getConnection();
             final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_USER)) {

            statement.setString(1, userAdriaId.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return userMapper.map(resultSet);
                } else throw new RepoAccessException("User not found.");
            }
        } catch (SQLException e) {
            throw new RepoAccessException("Error accessing user data", e);
        }
    }

    @Override
    public Set<User> getAllUsers() {
        try(final Connection connection = JdbcConnection.instance().getConnection();
            final PreparedStatement statement = connection.prepareStatement(QRY_SELECT_ALL_USERS);
            final ResultSet rs = statement.executeQuery()) {
            Set<User> users = new HashSet<>();

            while(rs.next()){
                users.add(userMapper.map(rs));
            }

            return users;
        } catch (SQLException e) {
            throw new RepoAccessException("Error accessing user data", e);
        }
    }

    @Override
    public void addUser(User user) throws RepoAccessException {
        UUID adriaId = user.getAdriaId();
        String fullName = user.getFullName();

        try(Connection connection = JdbcConnection.instance().getConnection();
            PreparedStatement statement = connection.prepareStatement(QRY_INSERT_USER)) {
            statement.setString(1, adriaId.toString());
            statement.setString(2, fullName);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepoAccessException(e);
        }
    }

}
