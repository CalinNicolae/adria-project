package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.User;

import java.util.Set;
import java.util.UUID;

public interface UsersRepo {

    User getUser(UUID userAdriaId) throws RepoAccessException;

    Set<User> getAllUsers();

    void addUser(User user) throws RepoAccessException;

}
