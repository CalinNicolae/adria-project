package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.StatisticsRepo;
import be.howest.adria.application.croptek.get_user_statistics.GetUserStatisticsInput;
import be.howest.adria.application.croptek.get_user_statistics.GetUserStatisticsUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

import java.util.UUID;

public class GetUserStatisticsController extends ControllerWithUseCase<GetUserStatisticsInput> {

    public GetUserStatisticsController(RoutingContext ctx, StatisticsRepo repo) {
        super(new GetUserStatisticsUC(new GetterPresenter<>(ctx), repo));
    }

    @Override
    protected GetUserStatisticsInput convertToUseCaseInput(Request request) {
        return new GetUserStatisticsInput(UUID.fromString(request.pathParam("adriaId")));
    }
}
