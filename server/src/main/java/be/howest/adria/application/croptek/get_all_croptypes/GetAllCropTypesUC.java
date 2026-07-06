package be.howest.adria.application.croptek.get_all_croptypes;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;

public class GetAllCropTypesUC implements UseCase<Void> {

    private final OutputPort<GetAllCropTypesOutput> presenter;
    private final CropTypesRepo repo;

    public GetAllCropTypesUC(OutputPort<GetAllCropTypesOutput> presenter, CropTypesRepo repo) {
        this.presenter = presenter;
        this.repo = repo;
    }

    @Override
    public void execute(Void input) {
       presenter.present(new GetAllCropTypesOutput(repo.getAllCropTypes()));
    }

}
