package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.rent_drone.RentDroneInput;
import be.howest.adria.application.croptek.rent_drone.RentDroneUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.UpdatePresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;
import java.util.UUID;

public class RentDroneController extends ControllerWithUseCase<RentDroneInput> {

    public RentDroneController(RoutingContext ctx, DronesRepo dronesRepo) {
        super(new RentDroneUC(new UpdatePresenter(ctx), dronesRepo));
    }

    @Override
    protected RentDroneInput convertToUseCaseInput(Request request) {
        return new RentDroneInput(UUID.fromString(request.pathParam("adriaId")));
    }

}
