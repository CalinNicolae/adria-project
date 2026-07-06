package be.howest.adria.application.croptek.get_planted_crops;

import be.howest.adria.domain.PlantedCrop;
import java.util.HashSet;
import java.util.Set;

public class GetPlantedCropsOutput extends HashSet<PlantedCrop> {

    public GetPlantedCropsOutput(Set<PlantedCrop> plantedCrops) {
        super(plantedCrops);
    }

}
