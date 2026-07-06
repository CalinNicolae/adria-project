package be.howest.adria.infrastructure.persistence.migrations;

import be.howest.adria.domain.User;
import be.howest.adria.infrastructure.repositories.TestCropTekRepo;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SeederTest {

    @Test
    void testSeedingUsers()
    {
        TestCropTekRepo repo = new TestCropTekRepo();
        Seeder seeder = new Seeder(repo);
        User user = new User(UUID.fromString("50b1871f-e600-4791-9566-787b029854b7"), "CropTek");

        seeder.seed();

        assertDoesNotThrow(() -> repo.getUsersRepo().getUser(UUID.fromString("50b1871f-e600-4791-9566-787b029854b7")));
        assertEquals(user, repo.getUsersRepo().getUser(UUID.fromString("50b1871f-e600-4791-9566-787b029854b7")));
    }

    @Test
    void testSeedingDrones(){
        TestCropTekRepo repo = new TestCropTekRepo();
        Seeder seeder = new Seeder(repo);

        seeder.seed();

        assertEquals(50, repo.getDronesRepo().getAllDrones().size());
    }

    @Test
    void testSeedingFarms(){
        TestCropTekRepo repo = new TestCropTekRepo();
        Seeder seeder = new Seeder(repo);

        seeder.seed();

        assertEquals(2, repo.getFarmsRepo().getAllFarms().size());
    }

    @Test
    void testSeedingFarmFields(){
        TestCropTekRepo repo = new TestCropTekRepo();
        Seeder seeder = new Seeder(repo);

        seeder.seed();

        assertEquals(4, repo.getFarmFieldsRepo().getAllFarmFields().size());
        assertEquals(2, repo.getFarmFieldsRepo().getFarmFields(1).size());
    }

    @Test
    void testSeedingPlantedCrops(){
        TestCropTekRepo repo = new TestCropTekRepo();
        Seeder seeder = new Seeder(repo);

        seeder.seed();

        assertNotEquals(401, repo.getPlantedCropsRepo().getAllPlantedCrops().size());
        assertNotEquals(101, repo.getPlantedCropsRepo().getPlantedCrops(1,1).size());
    }

    @Test
    void testSeedingCropTypes(){
        TestCropTekRepo repo = new TestCropTekRepo();
        Seeder seeder = new Seeder(repo);

        seeder.seed();

        assertEquals(4, repo.getCropTypesRepo().getAllCropTypes().size());
    }

}
