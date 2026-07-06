package be.howest.adria.infrastructure.persistence.migrations;

import java.security.SecureRandom;
import java.time.Instant;

import be.howest.adria.application.contracts.repositories.CropTekRepo;
import be.howest.adria.domain.CropType;
import be.howest.adria.domain.Drone;
import be.howest.adria.domain.DroneActivity;
import be.howest.adria.domain.Farm;
import be.howest.adria.domain.FarmField;
import be.howest.adria.domain.Notification;
import be.howest.adria.domain.PlantedCrop;
import be.howest.adria.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class Seeder {
    private final CropTekRepo repo;
    private final Random random = new SecureRandom();

    public Seeder(CropTekRepo repo) {
        this.repo = repo;
    }

    public void seed() {
        UUID userId = createUser();

        // Create farms
        Farm vegetableFarm = createFarm(userId, 1, "Vegetable Farm");
        Farm cerealFarm = createFarm(userId, 2, "Cereal Farm");

        // Create farm fields
        FarmField carrotField = createFarmField(vegetableFarm, 1, "Carrot Field");
        FarmField saladField = createFarmField(vegetableFarm, 2, "Salad Field");
        FarmField wheatField = createFarmField(cerealFarm, 1, "Wheat Field");
        FarmField cornField = createFarmField(cerealFarm, 2, "Corn Field");

        // Create crop types
        List<CropType> cropTypes = createCropTypes();

        // Map fields to crops
        Map<FarmField, CropType> fieldToCropMap = Map.of(
                carrotField, getCropTypeByName(cropTypes, "Carrot"),
                saladField, getCropTypeByName(cropTypes, "Salad"),
                wheatField, getCropTypeByName(cropTypes, "Wheat"),
                cornField, getCropTypeByName(cropTypes, "Corn")
        );

        // Create drones
        createDrones(userId);

        // Create planted crops
        createPlantedCrops(fieldToCropMap);

        // Create notifications
        createNotifications(userId);
    }

    private UUID createUser() {
        UUID userId = UUID.fromString("50b1871f-e600-4791-9566-787b029854b7");
        User user = new User(userId, "CropTek");
        repo.getUsersRepo().addUser(user);
        return userId;
    }

    private Farm createFarm(UUID userId, int farmId, String name) {
        Farm farm = new Farm(farmId, userId, name);
        repo.getFarmsRepo().addFarm(farm);
        return farm;
    }

    private FarmField createFarmField(Farm farm, int fieldId, String name) {
        FarmField field = new FarmField(fieldId, farm.getId(), name);
        repo.getFarmFieldsRepo().addFarmField(field);
        return field;
    }

    private List<CropType> createCropTypes() {
        List<CropType> cropTypes = List.of(
                new CropType(1, "Carrot", 10, 20),
                new CropType(2, "Salad", 20, 30),
                new CropType(3, "Wheat", 15, 25),
                new CropType(4, "Corn", 18, 28)
        );

        for (CropType type : cropTypes) {
            repo.getCropTypesRepo().addCropType(type);
        }
        return cropTypes;
    }

    private void createDrones(UUID userId) {
        final int nrOfUserDrones = 16;
        final int nrOfUnassignedDrones = 34;
        for (int i = 0; i < nrOfUserDrones; i++) {
            Drone drone = new Drone(UUID.randomUUID());
            drone.setUserId(Optional.of(userId));
            drone.setActivity(generateRandomDroneActivity());
            repo.getDronesRepo().addDrone(drone);
        }
        for (int i = 0; i < nrOfUnassignedDrones; i++) {
            Drone drone = new Drone(UUID.randomUUID());
            drone.setUserId(Optional.empty());
            drone.setActivity(generateRandomDroneActivity());
            repo.getDronesRepo().addDrone(drone);
        }
    }

    private void createPlantedCrops(Map<FarmField, CropType> fieldToCropMap) {
        fieldToCropMap.forEach((field, cropType) -> IntStream.rangeClosed(1, random.nextInt(100)).forEach(index -> {
            Instant now = Instant.now(); // Store the current time in a variable
            PlantedCrop crop = new PlantedCrop(
                    index,
                    cropType.getId(),
                    field.getFarmId(),
                    field.getId(),
                    now,
                    Optional.of(now)
            );
            repo.getPlantedCropsRepo().addPlantedCrop(crop);
        }));
    }

    private CropType getCropTypeByName(List<CropType> cropTypes, String name) {
        return cropTypes.stream()
                .filter(crop -> crop.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Crop type not found: " + name));
    }

    private void createNotifications(UUID userId){
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(
                1,
                userId,
                "Birds detected in the field",
                "A flock of birds has been detected near the crop field. This may pose a threat to your harvest. Consider deploying bird deterrents",
                false));

        notifications.add(new Notification(
                2,
                userId,
                "Weather alert",
                "Heavy rain expected in the next 24 hours.",
                false));

        notifications.add(new Notification(
                3,
                userId,
                "Crop disease detected",
                "Crop disease detected in the field. Please take necessary actions.",
                false));

        notifications.add(new Notification(
                4,
                userId,
                "Ground Ph level critically low",
                "Ensure to adjust the fertilizer based on the PH level.",
                false));

        notifications.add(new Notification(
                5,
                userId,
                "Yield update: Significant increase detected",
                "The drone has detected a significant increase in yield in the central region of your field. The yield has increased by 15% compared to last season. Please review the detailed yield analysis for more insights.",
                false));

        for(Notification notification : notifications){
            repo.getNotificationsRepo().addNotification(notification);
        }
    }

    private DroneActivity generateRandomDroneActivity(){
        DroneActivity[] activities = DroneActivity.values();
        int randomNumber = random.nextInt(activities.length);
        return activities[randomNumber];
    }
}
