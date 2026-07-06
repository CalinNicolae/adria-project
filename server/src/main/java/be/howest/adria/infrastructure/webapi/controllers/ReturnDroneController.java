package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.return_drone.ReturnDroneInput;
import be.howest.adria.application.croptek.return_drone.ReturnDroneUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.UpdatePresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;
import java.util.UUID;

public class ReturnDroneController extends ControllerWithUseCase<ReturnDroneInput> {

    public ReturnDroneController(RoutingContext ctx, DronesRepo dronesRepo) {
        super(new ReturnDroneUC(new UpdatePresenter(ctx), dronesRepo));
    }

    @Override
    protected ReturnDroneInput convertToUseCaseInput(Request request) {
        return new ReturnDroneInput(UUID.fromString(request.pathParam("droneId")));
    }

}
