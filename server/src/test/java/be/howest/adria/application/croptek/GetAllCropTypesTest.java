package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.croptek.get_all_croptypes.GetAllCropTypesOutput;
import be.howest.adria.application.croptek.get_all_croptypes.GetAllCropTypesUC;
import be.howest.adria.domain.CropType;
import be.howest.adria.infrastructure.repositories.tables.TestCropTypesRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GetAllCropTypesTest {

    private final CropTypesRepo repo = new TestCropTypesRepo();
    private final CropType potato = new CropType(1, "Potato", 10, 20);
    private final CropType salad = new CropType(2, "Salad", 20, 30);

    @BeforeEach
    void prepare(){
        repo.addCropType(potato);
        repo.addCropType(salad);
    }

    @Test
    void testGettingAllCropTypes(){
        final GetAllCropTypesUC uc = new GetAllCropTypesUC(this::handleResult, repo);
        uc.execute(null);
    }

    private void handleResult (GetAllCropTypesOutput result){
        assertTrue(result.contains(potato));
        assertTrue(result.contains(salad));
    }

}
