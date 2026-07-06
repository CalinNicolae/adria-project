package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.contracts.repositories.croptek.PlantedCropsRepo;
import be.howest.adria.application.contracts.repositories.croptek.StatisticsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.croptek.get_user_statistics.GetUserStatisticsOutput;
import be.howest.adria.application.croptek.get_user_statistics.GetUserStatisticsInput;
import be.howest.adria.application.croptek.get_user_statistics.GetUserStatisticsUC;
import be.howest.adria.infrastructure.repositories.tables.TestCropTypesRepo;
import be.howest.adria.infrastructure.repositories.tables.TestDronesRepo;
import be.howest.adria.infrastructure.repositories.tables.TestFarmsRepo;
import be.howest.adria.infrastructure.repositories.tables.TestPlantedCropsRepo;
import be.howest.adria.infrastructure.repositories.tables.TestStatisticsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetUserStatisticsTest {

    private OutputPort<GetUserStatisticsOutput> outputPort;
    private GetUserStatisticsUC useCase;
    private UUID userAdriaId;
    private StatisticsRepo statisticsRepo;

    @BeforeEach
    void prepare() {
        DronesRepo dronesRepo = new TestDronesRepo();
        FarmsRepo farmsRepo = new TestFarmsRepo();
        PlantedCropsRepo plantedCropsRepo = new TestPlantedCropsRepo();
        CropTypesRepo cropTypesRepo = new TestCropTypesRepo();
        statisticsRepo = new TestStatisticsRepo(dronesRepo, farmsRepo, plantedCropsRepo, cropTypesRepo);

        outputPort = output -> assertEquals(1, output.size());

        useCase = new GetUserStatisticsUC(outputPort, statisticsRepo);
        userAdriaId = UUID.randomUUID();
    }

    @Test
    void testGetUserStatisticsUCConstructor() {
        GetUserStatisticsUC testUseCase = new GetUserStatisticsUC(outputPort, statisticsRepo);

        assertEquals(statisticsRepo, testUseCase.getStatisticsRepo());
        assertEquals(outputPort, testUseCase.getOutputPort());
    }

    @Test
    void testExecuteWithValidInput() {
        GetUserStatisticsInput input = new GetUserStatisticsInput(userAdriaId);
        useCase.execute(input);

        outputPort = output -> assertEquals(1, output.size());
    }

    @Test
    void testConstructorWithNullOutputPort() {
        assertThrows(NullPointerException.class, () -> new GetUserStatisticsUC(null, statisticsRepo));
    }

    @Test
    void testConstructorWithNullStatisticsRepo() {
        assertThrows(NullPointerException.class, () -> new GetUserStatisticsUC(outputPort, null));
    }

}
