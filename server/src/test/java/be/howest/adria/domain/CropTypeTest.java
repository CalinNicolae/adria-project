package be.howest.adria.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CropTypeTest {

    @Test
    void testCreateCropType(){
        CropType type = new CropType(1, "Potato", 10, 20);

        assertEquals(1, type.getId());
        assertEquals("Potato", type.getName());
        assertEquals(10, type.getMinGrowthDays());
        assertEquals(20, type.getMaxGrowthDays());
    }

    @Test
    void testSetMinGrowthDays(){
        CropType type = new CropType(1, "Potato", 10, 20);

        type.setMinGrowthDays(20);

        assertEquals(20, type.getMinGrowthDays());
    }

    @Test
    void testSetMaxGrowthDays(){
        CropType type = new CropType(1, "Potato", 10, 20);

        type.setMaxGrowthDays(30);

        assertEquals(30, type.getMaxGrowthDays());
    }

    @Test
    void testEquality(){
        CropType type1 = new CropType(1, "Potato", 10, 20);
        CropType type2 = new CropType(1, "Patat", 20, 30);

        assertEquals(type1, type2);
    }

    @Test
    void testEqualityNotAlwaysTrue(){
        CropType type1 = new CropType(1, "Potato", 10, 20);
        CropType type2 = new CropType(2, "Tomato", 20, 30);

        assertNotEquals(type1, type2);
    }

    @Test
    void testSetNameWithValidName() {
        CropType cropType = new CropType(1, "Wheat", 10, 20);

        cropType.setName("Corn");

        assertEquals("Corn", cropType.getName());
    }

    @Test
    void testSetNameWithNull() {
        CropType cropType = new CropType(1, "Wheat", 10, 20);

        assertThrows(NullPointerException.class, () -> cropType.setName(null));
    }

    @Test
    void testEqualsWithNull() {
        CropType cropType = new CropType(1, "Wheat", 10, 20);

        assertNotEquals(null, cropType);
    }

    @Test
    void testEqualsWithDifferentClass() {
        CropType cropType = new CropType(1, "Wheat", 10, 20);

        String differentClassObject = "Not a CropType";

        assertNotEquals(differentClassObject, cropType);
    }

    @Test
    void testEqualsWithSameId() {
        CropType cropType1 = new CropType(1, "Wheat", 10, 20);
        CropType cropType2 = new CropType(1, "Corn", 15, 25);

        assertEquals(cropType1, cropType2);
    }

    @Test
    void testEqualsWithDifferentId() {
        CropType cropType1 = new CropType(1, "Wheat", 10, 20);
        CropType cropType2 = new CropType(2, "Wheat", 10, 20);

        assertNotEquals(cropType1, cropType2);
    }

    @Test
    void testHashCodeWithSameId() {
        CropType cropType1 = new CropType(1, "Wheat", 10, 20);
        CropType cropType2 = new CropType(1, "Corn", 15, 25);

        assertEquals(cropType1.hashCode(), cropType2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentId() {
        CropType cropType1 = new CropType(1, "Wheat", 10, 20);
        CropType cropType2 = new CropType(2, "Wheat", 10, 20);

        assertNotEquals(cropType1.hashCode(), cropType2.hashCode());
    }
}
