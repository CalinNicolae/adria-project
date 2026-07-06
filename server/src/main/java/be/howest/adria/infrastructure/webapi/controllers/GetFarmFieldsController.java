package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.FarmFieldsRepo;
import be.howest.adria.application.croptek.get_farm_fields.GetFarmFieldsInput;
import be.howest.adria.application.croptek.get_farm_fields.GetFarmFieldsUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

public class GetFarmFieldsController extends ControllerWithUseCase<GetFarmFieldsInput> {

    public GetFarmFieldsController(RoutingContext ctx, FarmFieldsRepo farmFieldsRepo) {
        super(new GetFarmFieldsUC(new GetterPresenter<>(ctx), farmFieldsRepo));
    }

    @Override
    protected GetFarmFieldsInput convertToUseCaseInput(Request request) {
        return new GetFarmFieldsInput(Integer.parseInt(request.pathParam("farmId")));
    }

}
