package be.howest.adria.main.factories;

import be.howest.adria.application.contracts.repositories.CropTekRepo;
import be.howest.adria.infrastructure.shared.contracts.Controller;
import be.howest.adria.infrastructure.webapi.controllers.RentDroneController;
import be.howest.adria.infrastructure.webapi.controllers.GetAllCropTypesController;
import be.howest.adria.infrastructure.webapi.controllers.GetAllDronesController;
import be.howest.adria.infrastructure.webapi.controllers.GetAllFarmsController;
import be.howest.adria.infrastructure.webapi.controllers.GetFarmFieldsController;
import be.howest.adria.infrastructure.webapi.controllers.GetPlantedCropsController;
import be.howest.adria.infrastructure.webapi.controllers.GetUserDronesController;
import be.howest.adria.infrastructure.webapi.controllers.GetUserFarmsController;
import be.howest.adria.infrastructure.webapi.controllers.GetUserNotificationsController;
import be.howest.adria.infrastructure.webapi.controllers.GetUserStatisticsController;
import be.howest.adria.infrastructure.webapi.controllers.ReturnDroneController;
import be.howest.adria.infrastructure.webapi.controllers.SetDroneActivityController;
import be.howest.adria.infrastructure.webapi.controllers.SetNotificationReadController;
import be.howest.adria.infrastructure.webapi.shared.Request;
import io.vertx.ext.web.RoutingContext;

public class WebApiControllerFactory {

    private final CropTekRepo cropTekRepo;

    public WebApiControllerFactory(CropTekRepo cropTekRepo) {
        this.cropTekRepo = cropTekRepo;
    }

    // This sonar smell will go away when you implement multiple controllers.
    public Controller<Request> createController(String operationId, RoutingContext ctx) {
        // This sonar smell will go away when you implement multiple controllers.
        switch (operationId) {
            case "getUserFarms":
                return new GetUserFarmsController(ctx, cropTekRepo.getFarmsRepo());
            case "getAllFarms":
                return new GetAllFarmsController(ctx, cropTekRepo.getFarmsRepo());
            case "getFarmFields":
                return new GetFarmFieldsController(ctx, cropTekRepo.getFarmFieldsRepo());
            case "getPlantedCrops":
                return new GetPlantedCropsController(ctx, cropTekRepo.getPlantedCropsRepo());
            case "getUserDrones":
                return new GetUserDronesController(ctx, cropTekRepo.getDronesRepo());
            case "getAllDrones":
                return new GetAllDronesController(ctx, cropTekRepo.getDronesRepo());
            case "returnDrone":
                return new ReturnDroneController(ctx, cropTekRepo.getDronesRepo());
            case "setDroneActivity":
                return new SetDroneActivityController(ctx, cropTekRepo.getDronesRepo());
            case "rentDrone":
                return new RentDroneController(ctx, cropTekRepo.getDronesRepo());
			case "getAllCropTypes":
				return new GetAllCropTypesController(ctx, cropTekRepo.getCropTypesRepo());
			case "setNotificationRead":
				return new SetNotificationReadController(ctx, cropTekRepo.getNotificationsRepo());
            case "getUserNotifications":
                return new GetUserNotificationsController(ctx, cropTekRepo.getNotificationsRepo());
            case "getUserStatistics":
                return new GetUserStatisticsController(ctx, cropTekRepo.getStatisticsRepo());
            default:
                throw new IllegalArgumentException("Unknown operationId: " + operationId);
        }
    }

}
