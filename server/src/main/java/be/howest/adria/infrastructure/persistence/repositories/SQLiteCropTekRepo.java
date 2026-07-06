package be.howest.adria.infrastructure.persistence.repositories;

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
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteCropTypesRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteDroneFailuresRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteDronesRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteFarmFieldsRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteFarmsRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteNotificationsRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLitePlantedCropsRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteStatisticsRepo;
import be.howest.adria.infrastructure.persistence.repositories.tables.SQLiteUsersRepo;

public class SQLiteCropTekRepo implements CropTekRepo {

    private final UsersRepo usersRepo;
    private final FarmsRepo farmsRepo;
    private final FarmFieldsRepo farmFieldsRepo;
    private final PlantedCropsRepo plantedCropsRepo;
    private final CropTypesRepo cropTypesRepo;
    private final DronesRepo dronesRepo;
    private final DroneFailuresRepo droneFailuresRepo;
    private final NotificationsRepo notificationsRepo;
    private final StatisticsRepo statisticsRepo;

    public SQLiteCropTekRepo() {
        this.usersRepo = new SQLiteUsersRepo();
        this.farmsRepo = new SQLiteFarmsRepo();
        this.farmFieldsRepo = new SQLiteFarmFieldsRepo();
        this.plantedCropsRepo = new SQLitePlantedCropsRepo();
        this.cropTypesRepo = new SQLiteCropTypesRepo();
        this.dronesRepo = new SQLiteDronesRepo();
        this.droneFailuresRepo = new SQLiteDroneFailuresRepo();
        this.notificationsRepo = new SQLiteNotificationsRepo();
        this.statisticsRepo = new SQLiteStatisticsRepo();
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
