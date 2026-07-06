package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.croptek.get_user_notifications.GetUserNotificationsInput;
import be.howest.adria.application.croptek.get_user_notifications.GetUserNotificationsUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.GetterPresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;
import java.util.UUID;

public class GetUserNotificationsController extends ControllerWithUseCase<GetUserNotificationsInput> {

    public GetUserNotificationsController(RoutingContext ctx, NotificationsRepo notificationsRepo) {
        super(new GetUserNotificationsUC(new GetterPresenter<>(ctx), notificationsRepo));
    }

    @Override
    protected GetUserNotificationsInput convertToUseCaseInput(Request request) {
        return new GetUserNotificationsInput(UUID.fromString(request.pathParam("adriaId")));
    }
}