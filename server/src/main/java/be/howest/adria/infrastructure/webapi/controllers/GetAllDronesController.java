package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.get_all_drones.GetAllDronesUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

public class GetAllDronesController extends ControllerWithUseCase<Void> {

    public GetAllDronesController(RoutingContext ctx, DronesRepo dronesRepo) {
        super(new GetAllDronesUC(new GetterPresenter<>(ctx), dronesRepo));
    }

    @Override
    protected Void convertToUseCaseInput(Request request) {
        return null;
    }
}
