package be.howest.adria.application.croptek.get_farm_fields;

import be.howest.adria.application.contracts.repositories.croptek.FarmFieldsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;

public class GetFarmFieldsUC implements UseCase<GetFarmFieldsInput> {

    private final OutputPort<GetFarmFieldsOutput> outputPort;
    private final FarmFieldsRepo farmFieldsRepo;

    public GetFarmFieldsUC(OutputPort<GetFarmFieldsOutput> outputPort, FarmFieldsRepo farmFieldsRepo) {
        this.outputPort = outputPort;
        this.farmFieldsRepo = farmFieldsRepo;
    }

    @Override
    public void execute(GetFarmFieldsInput input) {
        outputPort.present(
                new GetFarmFieldsOutput(
                        farmFieldsRepo.getFarmFields(input.getFarmId())
                )
        );
    }

}
