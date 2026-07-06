package be.howest.adria.infrastructure.repositories.tables;


import be.howest.adria.application.contracts.repositories.croptek.UsersRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class TestUsersRepo implements UsersRepo {

    private final Set<User> users = new HashSet<>();

    @Override
    public User getUser(UUID userAdriaId) throws RepoAccessException {
        final Optional<User> userFound = getUserOptional(userAdriaId);
        if (userFound.isPresent()) {
            return userFound.get();
        }else {
            throw new RepoAccessException("User not found");
        }
    }

    @Override
    public Set<User> getAllUsers() {
        return Collections.unmodifiableSet(users);
    }

    @Override
    public void addUser(User user) throws RepoAccessException {
        if(getUserOptional(user.getAdriaId()).isPresent()) {
            throw new RepoAccessException("User already exists");
        }else{
            users.add(user);
        }
    }

    private Optional<User> getUserOptional(UUID userAdriaId) {
        for(User user : users) {
            if(user.getAdriaId().equals(userAdriaId)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

}
