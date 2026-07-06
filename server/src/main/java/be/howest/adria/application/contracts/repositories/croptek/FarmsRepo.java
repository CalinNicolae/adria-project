package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.Farm;
import java.util.Set;
import java.util.UUID;

public interface FarmsRepo {

    Set<Farm> getFarms(UUID userAdriaId) throws RepoAccessException;

    Set<Farm> getAllFarms() throws RepoAccessException;

    void addFarm(Farm farm) throws RepoAccessException;

}
