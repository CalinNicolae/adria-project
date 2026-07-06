package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.croptek.get_user_farms.GetUserFarmsInput;
import be.howest.adria.application.croptek.get_user_farms.GetUserFarmsOutput;
import be.howest.adria.application.croptek.get_user_farms.GetUserFarmsUC;
import be.howest.adria.domain.Farm;
import be.howest.adria.infrastructure.repositories.tables.TestFarmsRepo;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetUserFarmsTest {

    private final FarmsRepo farmsRepo = new TestFarmsRepo();

    private final UUID userId1 = UUID.randomUUID();
    private final UUID userId2 = UUID.randomUUID();

    private final Farm farm1 = new Farm(1, userId1, "Carrot Farm");
    private final Farm farm2 = new Farm(2, userId2, "Potato Farm");
    private final Farm farm3 = new Farm(3, userId2, "Beetroot Farm");

    @BeforeEach
    void prepare() {
        farmsRepo.addFarm(farm1);
        farmsRepo.addFarm(farm2);
        farmsRepo.addFarm(farm3);
    }

    @Test
    void getUserFarms() {
        final GetUserFarmsUC uc = new GetUserFarmsUC(this::evaluate_getUserFarms, farmsRepo);
        uc.execute(new GetUserFarmsInput(userId2));
    }

    private void evaluate_getUserFarms(GetUserFarmsOutput data) {
        Assertions.assertTrue(!data.contains(farm1) && data.contains(farm2) && data.contains(farm3));
    }

}
