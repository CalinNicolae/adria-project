package be.howest.adria.application.croptek.get_user_drones;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;

public class GetUserDronesUC implements UseCase<GetUserDronesInput> {

    private final OutputPort<GetUserDronesOutput> outputPort;
    private final DronesRepo dronesRepo;

    public GetUserDronesUC(OutputPort<GetUserDronesOutput> outputPort, DronesRepo dronesRepo){
        this.outputPort = outputPort;
        this.dronesRepo = dronesRepo;
    }

    @Override
    public void execute(GetUserDronesInput input) {
        outputPort.present(
                new GetUserDronesOutput(
                        dronesRepo.getAllDronesForUserId(input.getUserID())
                )
        );
    }

}
