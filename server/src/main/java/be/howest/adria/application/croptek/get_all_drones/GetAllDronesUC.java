package be.howest.adria.application.croptek.get_all_drones;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;

public class GetAllDronesUC implements UseCase<Void> {

    private final OutputPort<GetAllDronesOutput> presenter;
    private final DronesRepo dronesRepo;

    public GetAllDronesUC(OutputPort<GetAllDronesOutput> presenter, DronesRepo dronesRepo) {
        this.presenter = presenter;
        this.dronesRepo = dronesRepo;
    }

    @Override
    public void execute(Void input) {
        presenter.present(
                new GetAllDronesOutput(
                        dronesRepo.getAllDrones()
                )
        );
    }

}
