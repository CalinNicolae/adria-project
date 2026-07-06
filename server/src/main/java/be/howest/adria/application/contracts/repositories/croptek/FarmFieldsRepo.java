package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.FarmField;
import java.util.Set;

public interface FarmFieldsRepo {

    Set<FarmField> getFarmFields(int farmId) throws RepoAccessException;

    Set<FarmField> getAllFarmFields();

    void addFarmField(FarmField farmField) throws RepoAccessException;

}
