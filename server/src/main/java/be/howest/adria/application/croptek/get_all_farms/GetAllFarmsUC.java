package be.howest.adria.application.croptek.get_all_farms;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;

public class GetAllFarmsUC implements UseCase<Void> {

    private final OutputPort<GetAllFarmsOutput> presenter;
    private final FarmsRepo farmsRepo;

    public GetAllFarmsUC(OutputPort<GetAllFarmsOutput> presenter, FarmsRepo farmsRepo) {
        this.presenter = presenter;
        this.farmsRepo = farmsRepo;
    }

    @Override
    public void execute(Void input) {
        presenter.present(new GetAllFarmsOutput(farmsRepo.getAllFarms()));
    }
}