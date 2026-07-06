package be.howest.adria.application.contracts.repositories;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.contracts.repositories.croptek.DroneFailuresRepo;
import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.repositories.croptek.FarmFieldsRepo;
import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.contracts.repositories.croptek.StatisticsRepo;
import be.howest.adria.application.contracts.repositories.croptek.UsersRepo;

public interface CropTekRepo {

    UsersRepo getUsersRepo();

    FarmsRepo getFarmsRepo();

    FarmFieldsRepo getFarmFieldsRepo();

    PlantedCropsRepo getPlantedCropsRepo();

    CropTypesRepo getCropTypesRepo();

    DronesRepo getDronesRepo();

    DroneFailuresRepo getDroneFailuresRepo();

    NotificationsRepo getNotificationsRepo();

    StatisticsRepo getStatisticsRepo();

}
