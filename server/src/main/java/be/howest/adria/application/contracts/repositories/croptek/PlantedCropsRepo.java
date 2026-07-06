package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.PlantedCrop;
import java.util.Set;

public interface PlantedCropsRepo {

    Set<PlantedCrop> getPlantedCrops(int farmFieldId, int fieldId) throws RepoAccessException;

    Set<PlantedCrop> getPlantedCrops(int farmId) throws RepoAccessException;

    Set<PlantedCrop> getAllPlantedCrops() throws RepoAccessException;

    void addPlantedCrop(PlantedCrop plantedCrop) throws RepoAccessException;

}
