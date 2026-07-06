package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.croptek.get_planted_crops.GetPlantedCropsInput;
import be.howest.adria.application.croptek.get_planted_crops.GetPlantedCropsOutput;
import be.howest.adria.application.croptek.get_planted_crops.GetPlantedCropsUC;
import be.howest.adria.domain.PlantedCrop;
import be.howest.adria.infrastructure.repositories.tables.TestPlantedCropsRepo;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetPlantedCropsTest {

    private final PlantedCropsRepo plantedCropsRepo = new TestPlantedCropsRepo();

    private final PlantedCrop cornPlant1 = new PlantedCrop(1, 1, 1, 1, Instant.now(), Optional.empty());
    private final PlantedCrop cornPlant2 = new PlantedCrop(1, 1, 2, 1, Instant.now(), Optional.empty());

    @BeforeEach
    void prepare() {
        plantedCropsRepo.addPlantedCrop(cornPlant1);
        plantedCropsRepo.addPlantedCrop(cornPlant2);
    }

    @Test
    void getPlantedCrops() {
        final GetPlantedCropsUC uc = new GetPlantedCropsUC(this::evaluate_getPlantedCrops, plantedCropsRepo);
        uc.execute(new GetPlantedCropsInput(1, 1));
    }

    private void evaluate_getPlantedCrops(GetPlantedCropsOutput data) {
        Assertions.assertTrue(data.contains(cornPlant1) && !data.contains(cornPlant2));
    }

}
