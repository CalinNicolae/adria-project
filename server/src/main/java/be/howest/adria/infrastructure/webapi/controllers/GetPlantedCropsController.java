package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.croptek.get_planted_crops.GetPlantedCropsInput;
import be.howest.adria.application.croptek.get_planted_crops.GetPlantedCropsUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

public class GetPlantedCropsController extends ControllerWithUseCase<GetPlantedCropsInput> {

    public GetPlantedCropsController(RoutingContext ctx, PlantedCropsRepo plantedCropsRepo) {
        super(new GetPlantedCropsUC(new GetterPresenter<>(ctx), plantedCropsRepo));
    }

    @Override
    protected GetPlantedCropsInput convertToUseCaseInput(Request request) {
        return new GetPlantedCropsInput(
                Integer.parseInt(request.pathParam("farmId")),
                Integer.parseInt(request.pathParam("farmFieldId"))
        );
    }
}
