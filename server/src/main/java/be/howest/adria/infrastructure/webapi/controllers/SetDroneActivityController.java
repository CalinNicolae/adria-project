package be.howest.adria.infrastructure.webapi.controllers;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.croptek.set_drone_activity.SetDroneActivityInput;
import be.howest.adria.application.croptek.set_drone_activity.SetDroneActivityUC;
import be.howest.adria.domain.DroneActivity;
import be.howest.adria.infrastructure.webapi.controllers.util.ControllerWithUseCase;
import be.howest.adria.infrastructure.webapi.presenters.util.UpdatePresenter;
import be.howest.adria.infrastructure.webapi.shared.Request;

import io.vertx.ext.web.RoutingContext;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class SetDroneActivityController extends ControllerWithUseCase<SetDroneActivityInput> {
    private static final Random random = new SecureRandom();

    public SetDroneActivityController(RoutingContext ctx, DronesRepo dronesRepo) {
        super(new SetDroneActivityUC(new UpdatePresenter(ctx), dronesRepo));
    }

    @Override
    public SetDroneActivityInput convertToUseCaseInput(Request request) {
        if(request.body().getString("activity").equals("AUTO")){
            return new SetDroneActivityInput(
                    UUID.fromString(request.pathParam("droneId")),
                    DroneActivity.values()[random.nextInt(DroneActivity.values().length)]
            );
        }

        return new SetDroneActivityInput(
                UUID.fromString(request.pathParam("droneId")),
                DroneActivity.valueOf(request.body().getString("activity"))
                );
    }
}
