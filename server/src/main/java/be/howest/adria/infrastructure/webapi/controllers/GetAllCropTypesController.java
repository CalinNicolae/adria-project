package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.croptek.get_all_croptypes.GetAllCropTypesUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

public class GetAllCropTypesController extends ControllerWithUseCase<Void> {


    public GetAllCropTypesController(RoutingContext ctx, CropTypesRepo repo) {
        super(new GetAllCropTypesUC(new GetterPresenter<>(ctx), repo));
    }

    @Override
    protected Void convertToUseCaseInput(Request request) {
        return null;
    }
}
