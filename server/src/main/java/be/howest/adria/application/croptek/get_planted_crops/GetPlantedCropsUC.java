package be.howest.adria.application.croptek.get_planted_crops;

import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;
import be.howest.adria.domain.PlantedCrop;
import java.util.Set;

public class GetPlantedCropsUC implements UseCase<GetPlantedCropsInput> {

    private final OutputPort<GetPlantedCropsOutput> outputPort;
    private final PlantedCropsRepo plantedCropsRepo;

    public GetPlantedCropsUC(OutputPort<GetPlantedCropsOutput> outputPort, PlantedCropsRepo plantedCropsRepo) {
        this.outputPort = outputPort;
        this.plantedCropsRepo = plantedCropsRepo;
    }

    @Override
    public void execute(GetPlantedCropsInput input) {
        Set<PlantedCrop> plantedCrops = plantedCropsRepo.getPlantedCrops(input.getFarmId(), input.getFarmFieldId());
        GetPlantedCropsOutput output = new GetPlantedCropsOutput(plantedCrops);
        outputPort.present(output);
    }

}
