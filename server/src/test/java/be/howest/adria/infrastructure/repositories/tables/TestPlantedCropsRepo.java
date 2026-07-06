package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.PlantedCrop;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestPlantedCropsRepo implements PlantedCropsRepo {

    private final Set<PlantedCrop> plantedCrops = new HashSet<>();

    @Override
    public Set<PlantedCrop> getPlantedCrops(int farmId, int farmFieldId) throws RepoAccessException {
        final Set<PlantedCrop> plantedCropsFound = new HashSet<>();

        for(PlantedCrop plantedCrop : getPlantedCrops(farmId)) {
            if(plantedCrop.getFarmFieldId() == farmFieldId) {
                plantedCropsFound.add(plantedCrop);
            }
        }

        return plantedCropsFound;
    }

    @Override
    public Set<PlantedCrop> getPlantedCrops(int farmId) throws RepoAccessException {
        final Set<PlantedCrop> plantedCropsFound = new HashSet<>();

        for(PlantedCrop plantedCrop : plantedCrops) {
            if(plantedCrop.getFarmId() == farmId) {
                plantedCropsFound.add(plantedCrop);
            }
        }

        return plantedCropsFound;
    }

    @Override
    public Set<PlantedCrop> getAllPlantedCrops() {
        return Collections.unmodifiableSet(plantedCrops);
    }

    @Override
    public void addPlantedCrop(PlantedCrop plantedCrop) throws RepoAccessException {
        plantedCrops.add(plantedCrop);
    }

}
