package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.croptek.set_notification_read.SetNotificationReadInput;
import be.howest.adria.application.croptek.set_notification_read.SetNotificationReadUC;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.UpdatePresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

public class SetNotificationReadController extends ControllerWithUseCase<SetNotificationReadInput> {

    public SetNotificationReadController(RoutingContext ctx, NotificationsRepo notificationsRepo) {
        super(new SetNotificationReadUC(new UpdatePresenter(ctx), notificationsRepo));
    }

    @Override
    public SetNotificationReadInput convertToUseCaseInput(Request request) {
        return new SetNotificationReadInput(
                Integer.parseInt(request.pathParam("notificationId")),
                Boolean.parseBoolean(request.body().getString("read"))
                );
    }

}
