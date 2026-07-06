package be.howest.adria.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FarmFieldTest {

    @Test
    void testCreateFarmField(){
        Farm farm = new Farm(1, UUID.randomUUID(), "Bob's MegaFarm");

        FarmField field = new FarmField(1, farm, "Potato Field");

        assertEquals(1, field.getId());
        assertEquals(farm.getId(), field.getFarmId());
        assertEquals("Potato Field", field.getName());
    }

    @Test
    void testCreateFarmFieldWithFarmId() {
        FarmField field = new FarmField(1, 1, "Corn Field");

        assertEquals(1, field.getId());
        assertEquals(1, field.getFarmId());
        assertEquals("Corn Field", field.getName());
    }

    @Test
    void testEquality(){
        FarmField field1 = new FarmField(1, 1, "Potato Field");
        FarmField field2 = new FarmField(1, 1, "Pattatenveld");

        assertEquals(field1, field2);
    }

    @Test
    void testEqualityNotAlwaysTrue(){
        FarmField field1 = new FarmField(1, 1, "Potato Field");
        FarmField field2 = new FarmField(2, 1, "Salted Earth from the apocalypse");

        assertNotEquals(field1, field2);
    }

    @Test
    void testEqualsWithNull() {
        FarmField field = new FarmField(1, 1, "Potato Field");

        assertNotEquals(null, field);
    }

    @Test
    void testEqualsWithDifferentClass() {
        FarmField field = new FarmField(1, 1, "Potato Field");

        String differentClassObject = "Not a FarmField";

        assertNotEquals(differentClassObject, field);
    }

    @Test
    void testEqualsWithDifferentFarmId() {
        FarmField field1 = new FarmField(1, 1, "Potato Field");
        FarmField field2 = new FarmField(1, 2, "Potato Field");

        assertNotEquals(field1, field2);
    }

    @Test
    void testHashCodeConsistency() {
        FarmField field1 = new FarmField(1, 1, "Potato Field");
        FarmField field2 = new FarmField(1, 1, "Pattatenveld");

        assertEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentValues() {
        FarmField field1 = new FarmField(1, 1, "Potato Field");
        FarmField field2 = new FarmField(2, 1, "Potato Field");

        assertNotEquals(field1.hashCode(), field2.hashCode());
    }

}
