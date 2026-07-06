package be.howest.adria.infrastructure.repositories;

import be.howest.adria.application.contracts.repositories.CropTekRepo;
import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.contracts.repositories.croptek.DroneFailuresRepo;
import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.repositories.croptek.FarmFieldsRepo;
import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.contracts.repositories.croptek.StatisticsRepo;
import be.howest.adria.application.contracts.repositories.croptek.UsersRepo;
import be.howest.adria.infrastructure.repositories.tables.TestCropTypesRepo;
import be.howest.adria.infrastructure.repositories.tables.TestDroneFailuresRepo;
import be.howest.adria.infrastructure.repositories.tables.TestDronesRepo;
import be.howest.adria.infrastructure.repositories.tables.TestFarmFieldsRepo;
import be.howest.adria.infrastructure.repositories.tables.TestFarmsRepo;
import be.howest.adria.infrastructure.repositories.tables.TestNotificationsRepo;
import be.howest.adria.infrastructure.repositories.tables.TestPlantedCropsRepo;
import be.howest.adria.infrastructure.repositories.tables.TestStatisticsRepo;
import be.howest.adria.infrastructure.repositories.tables.TestUsersRepo;

public class TestCropTekRepo implements CropTekRepo {

    private final UsersRepo usersRepo;
    private final FarmsRepo farmsRepo;
    private final FarmFieldsRepo farmFieldsRepo;
    private final PlantedCropsRepo plantedCropsRepo;
    private final CropTypesRepo cropTypesRepo;
    private final DronesRepo dronesRepo;
    private final DroneFailuresRepo droneFailuresRepo;
    private final NotificationsRepo notificationsRepo;
    private final StatisticsRepo statisticsRepo;

    public TestCropTekRepo(){
        usersRepo = new TestUsersRepo();
        farmsRepo = new TestFarmsRepo();
        farmFieldsRepo = new TestFarmFieldsRepo();
        plantedCropsRepo = new TestPlantedCropsRepo();
        cropTypesRepo = new TestCropTypesRepo();
        dronesRepo = new TestDronesRepo();
        droneFailuresRepo = new TestDroneFailuresRepo();
        notificationsRepo = new TestNotificationsRepo();
        statisticsRepo = new TestStatisticsRepo
                (dronesRepo, farmsRepo, plantedCropsRepo, cropTypesRepo);
    }

    @Override
    public UsersRepo getUsersRepo() {
        return usersRepo;
    }

    @Override
    public FarmsRepo getFarmsRepo() {
        return farmsRepo;
    }

    @Override
    public FarmFieldsRepo getFarmFieldsRepo() {
        return farmFieldsRepo;
    }

    @Override
    public PlantedCropsRepo getPlantedCropsRepo() {
        return plantedCropsRepo;
    }

    @Override
    public CropTypesRepo getCropTypesRepo() {
        return cropTypesRepo;
    }

    @Override
    public DronesRepo getDronesRepo() {
        return dronesRepo;
    }

    @Override
    public DroneFailuresRepo getDroneFailuresRepo() {
        return droneFailuresRepo;
    }

    @Override
    public NotificationsRepo getNotificationsRepo() {
        return notificationsRepo;
    }

    @Override
    public StatisticsRepo getStatisticsRepo() {
        return statisticsRepo;
    }

}
