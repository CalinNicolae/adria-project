package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.FarmFieldsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.FarmField;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestFarmFieldsRepo implements FarmFieldsRepo {

    private final Set<FarmField> farmFields = new HashSet<>();

    @Override
    public Set<FarmField> getFarmFields(int farmId) throws RepoAccessException {
        final Set<FarmField> farmFieldsFound = new HashSet<>();

        for(FarmField farmField : farmFields) {
            if(farmField.getFarmId() == farmId) {
                farmFieldsFound.add(farmField);
            }
        }

        return farmFieldsFound;
    }

    @Override
    public Set<FarmField> getAllFarmFields() {
        return Collections.unmodifiableSet(farmFields);
    }

    @Override
    public void addFarmField(FarmField farmField) throws RepoAccessException {
        farmFields.add(farmField);
    }

}
