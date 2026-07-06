package be.howest.adria.application.croptek.get_user_farms;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;

public class GetUserFarmsUC implements UseCase<GetUserFarmsInput> {

    private final OutputPort<GetUserFarmsOutput> outputPort;
    private final FarmsRepo farmsRepo;

    public GetUserFarmsUC(OutputPort<GetUserFarmsOutput> outputPort, FarmsRepo farmsRepo) {
        this.outputPort = outputPort;
        this.farmsRepo = farmsRepo;
    }

    @Override
    public void execute(GetUserFarmsInput input) {
        outputPort.present(
                new GetUserFarmsOutput(
                        farmsRepo.getFarms(input.getUserAdriaId())
                )
        );
    }

}
