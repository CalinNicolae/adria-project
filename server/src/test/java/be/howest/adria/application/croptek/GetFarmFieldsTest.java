package be.howest.adria.application.croptek;

import be.howest.adria.application.contracts.repositories.croptek.FarmFieldsRepo;
import be.howest.adria.application.croptek.get_farm_fields.GetFarmFieldsInput;
import be.howest.adria.application.croptek.get_farm_fields.GetFarmFieldsOutput;
import be.howest.adria.application.croptek.get_farm_fields.GetFarmFieldsUC;
import be.howest.adria.domain.FarmField;
import be.howest.adria.infrastructure.repositories.tables.TestFarmFieldsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GetFarmFieldsTest {

    private final FarmFieldsRepo repo = new TestFarmFieldsRepo();
    private final FarmField farmField1 = new FarmField(1,1, "TestFarm1");
    private final FarmField farmField2 = new FarmField(2, 2, "test farm field 2");
    @BeforeEach
    void prepare(){
        repo.addFarmField(farmField1);
        repo.addFarmField(farmField2);
    }

    @Test
    void testGettingFarmFields(){
        GetFarmFieldsUC useCase = new GetFarmFieldsUC(this::checkResults, repo);
        useCase.execute(new GetFarmFieldsInput(1));
    }

    private void checkResults(GetFarmFieldsOutput result){
        assertTrue(result.contains(farmField1));
        assertFalse(result.contains(farmField2));
    }

}
