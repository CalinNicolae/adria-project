package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.croptek.get_all_farms.GetAllFarmsUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

public class GetAllFarmsController extends ControllerWithUseCase<Void> {

    public GetAllFarmsController(RoutingContext ctx, FarmsRepo farmsRepo) {
        super(new GetAllFarmsUC(new GetterPresenter<>(ctx), farmsRepo));
    }

    @Override
    protected Void convertToUseCaseInput(Request request) {
        return null;
    }
}
