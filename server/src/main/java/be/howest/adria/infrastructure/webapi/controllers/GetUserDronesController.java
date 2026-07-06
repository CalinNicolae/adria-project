package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.get_user_drones.GetUserDronesInput;
import be.howest.adria.application.croptek.get_user_drones.GetUserDronesUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

public class GetUserDronesController extends ControllerWithUseCase<GetUserDronesInput> {

    public GetUserDronesController(RoutingContext ctx, DronesRepo dronesRepo){
        super(new GetUserDronesUC(new GetterPresenter<>(ctx), dronesRepo));
    }

    @Override
    protected GetUserDronesInput convertToUseCaseInput(Request request) {
        return new GetUserDronesInput(UUID.fromString(request.pathParam("adriaId")));
    }

}
