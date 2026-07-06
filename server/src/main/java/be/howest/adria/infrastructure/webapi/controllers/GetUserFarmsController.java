package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.croptek.get_user_farms.GetUserFarmsInput;
import be.howest.adria.application.croptek.get_user_farms.GetUserFarmsUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;
import java.util.UUID;

public class GetUserFarmsController extends ControllerWithUseCase<GetUserFarmsInput> {

    public GetUserFarmsController(RoutingContext ctx, FarmsRepo farmsRepo) {
        super(new GetUserFarmsUC(new GetterPresenter<>(ctx), farmsRepo));
    }

    @Override
    protected GetUserFarmsInput convertToUseCaseInput(Request request) {
        return new GetUserFarmsInput(UUID.fromString(request.pathParam("adriaId")));
    }

}
