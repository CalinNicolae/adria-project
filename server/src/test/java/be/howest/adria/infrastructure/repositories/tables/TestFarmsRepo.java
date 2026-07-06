package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Farm;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestFarmsRepo implements FarmsRepo {

    private final Set<Farm> farms = new HashSet<>();

    @Override
    public Set<Farm> getFarms(UUID userAdriaId) throws RepoAccessException {
        final Set<Farm> farmsFound = new HashSet<>();

        for (Farm farm : farms) {
            if(farm.getUserId().equals(userAdriaId)) {
                farmsFound.add(farm);
            }
        }

        return farmsFound;
    }

    @Override
    public Set<Farm> getAllFarms() throws RepoAccessException {
        return Collections.unmodifiableSet(farms);
    }

    @Override
    public void addFarm(Farm farm) throws RepoAccessException {
        farms.add(farm);
    }

}
