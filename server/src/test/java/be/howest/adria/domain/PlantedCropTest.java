package be.howest.adria.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlantedCropTest {

    @Test
    void testCreatePlantedCrop(){
        CropType type = new CropType(1, "Potato",10, 20);
        Farm farm = new Farm(1, UUID.randomUUID(), "Bob's MegaFarm");
        FarmField field = new FarmField(1, farm, "Potato field");

        PlantedCrop crop = new PlantedCrop(1, type, farm, field, Instant.EPOCH, Optional.empty());

        assertEquals(1, crop.getId());
        assertEquals(type.getId(), crop.getTypeId());
        assertEquals(farm.getId(), crop.getFarmId());
        assertEquals(field.getId(), crop.getFarmFieldId());
        assertEquals(Instant.EPOCH, crop.getPlanted());
        assertFalse(crop.getHarvest().isPresent());
    }

    @Test
    void testSetHarvest(){
        PlantedCrop crop = new PlantedCrop(1,1,1,1, Instant.EPOCH,Optional.empty());

        crop.setHarvest(Optional.of(Instant.EPOCH));

        assertTrue(crop.getHarvest().isPresent());
        assertEquals(Instant.EPOCH, crop.getHarvest().get());
    }

    @Test
    void testSetPlanted(){
        PlantedCrop crop = new PlantedCrop(1,1,1,1,Instant.now(), Optional.empty());

        crop.setPlanted(Instant.EPOCH);

        assertEquals(Instant.EPOCH, crop.getPlanted());
    }

    @Test
    void testEquality(){
        PlantedCrop crop1 = new PlantedCrop(1,1,1,1, Instant.EPOCH, Optional.empty());
        PlantedCrop crop2 = new PlantedCrop(1,1,1,1, Instant.EPOCH, Optional.empty());

        assertEquals(crop1, crop2);
    }

    @Test
    void testEqualityNotAlwaysTrue(){
        PlantedCrop crop1 = new PlantedCrop(1,1,1,1, Instant.EPOCH, Optional.empty());
        PlantedCrop crop2 = new PlantedCrop(1,1,1,2, Instant.EPOCH, Optional.empty());

        assertNotEquals(crop1, crop2);
    }

    @Test
    void testEqualsWithNull() {
        PlantedCrop plantedCrop = new PlantedCrop(1, 2, 3, 4, Instant.now(), Optional.empty());

        assertNotEquals(null, plantedCrop);
    }

    @Test
    void testEqualsWithDifferentClass() {
        PlantedCrop plantedCrop = new PlantedCrop(1, 2, 3, 4, Instant.now(), Optional.empty());

        String differentClassObject = "Not a PlantedCrop";
        assertNotEquals(differentClassObject, plantedCrop);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Instant now = Instant.now();

        PlantedCrop crop1 = new PlantedCrop(1, 2, 3, 4, now, Optional.empty());
        PlantedCrop crop2 = new PlantedCrop(5, 6, 7, 8, now, Optional.empty());

        assertNotEquals(crop1, crop2);
    }

    @Test
    void testEqualsWithSameValues() {
        Instant now = Instant.now();

        PlantedCrop crop1 = new PlantedCrop(1, 2, 3, 4, now, Optional.empty());
        PlantedCrop crop2 = new PlantedCrop(1, 2, 3, 4, now, Optional.empty());

        assertEquals(crop1, crop2);
    }

    @Test
    void testHashCodeWithSameValues() {
        Instant now = Instant.now();

        PlantedCrop crop1 = new PlantedCrop(1, 2, 3, 4, now, Optional.empty());
        PlantedCrop crop2 = new PlantedCrop(1, 2, 3, 4, now, Optional.empty());

        assertEquals(crop1.hashCode(), crop2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentValues() {
        Instant now = Instant.now();

        PlantedCrop crop1 = new PlantedCrop(1, 2, 3, 4, now, Optional.empty());
        PlantedCrop crop2 = new PlantedCrop(5, 6, 7, 8, now, Optional.empty());

        assertNotEquals(crop1.hashCode(), crop2.hashCode());
    }
}
