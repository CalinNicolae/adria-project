package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.FarmsRepo;
import be.howest.adria.application.croptek.get_all_farms.GetAllFarmsOutput;
import be.howest.adria.application.croptek.get_all_farms.GetAllFarmsUC;
import be.howest.adria.domain.Farm;
import be.howest.adria.infrastructure.repositories.tables.TestFarmsRepo;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetAllFarmsTest {

    private final FarmsRepo farmsRepo = new TestFarmsRepo();

    private final UUID userId1 = UUID.randomUUID();
    private final UUID userId2 = UUID.randomUUID();

    private final Farm farm1 = new Farm(1, userId1, "Fruit Farm");
    private final Farm farm2 = new Farm(2, userId2, "Banana Farm");
    private final Farm farm3 = new Farm(2, userId2, "Pear Trees");// Should not be added
    private final Farm farm4 = new Farm(3, userId1, "Potato Fields");

    @BeforeEach
    void prepare() {
        farmsRepo.addFarm(farm1);
        farmsRepo.addFarm(farm2);
        farmsRepo.addFarm(farm3);
        farmsRepo.addFarm(farm4);
    }

    @Test
    void getAllFarms() {
        final GetAllFarmsUC uc = new GetAllFarmsUC(this::evaluate_getAllFarms, farmsRepo);
        uc.execute(null);
    }

    private void evaluate_getAllFarms(GetAllFarmsOutput data) {
        Assertions.assertTrue(data.contains(farm1) && data.contains(farm2) &&
                        // Pear trees has same identity as banana farm so it should not be added.
                        data.contains(farm4));
    }

    @Test
    void noDuplicates() {
        final GetAllFarmsUC uc = new GetAllFarmsUC(this::evaluate_noDuplicates, farmsRepo);
        uc.execute(null);
    }

    private void evaluate_noDuplicates(GetAllFarmsOutput data) {
        Assertions.assertEquals(3, data.size());
    }

}
