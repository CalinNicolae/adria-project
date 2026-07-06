package be.howest.adria.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FarmTest {

    @Test
    void testCreateFarm(){
        UUID userId = UUID.randomUUID();

        Farm farm = new Farm(1, userId, "Bob's Mega farm");

        assertEquals(1, farm.getId());
        assertEquals(userId, farm.getUserId());
        assertEquals("Bob's Mega farm", farm.getName());
    }

    @Test
    void testSetName(){
        Farm farm = new Farm(1, UUID.randomUUID(), "Bob's Mega farm");

        farm.setName("Bob's Giga farm");

        assertEquals("Bob's Giga farm", farm.getName());
    }

    @Test
    void testEquality(){
        UUID userId = UUID.randomUUID();
        Farm farm1 = new Farm(1, userId, "Bob's Mega Farm");
        Farm farm2 = new Farm(1, userId, "Bob's Giga Farm");

        assertEquals(farm1, farm2);
    }

    @Test
    void testEqualityNotAlwaysTrue(){
        UUID userId = UUID.randomUUID();

        Farm farm1 = new Farm(1, userId, "Bob's Mega Farm");
        Farm farm2 =   new Farm(2, userId, "Bob's \"Secret\" Farm");

        assertNotEquals(farm1, farm2);
    }

    @Test
    void testCreateFarmWithUser() {
        User user = new User(UUID.randomUUID(), "Bob");

        Farm farm = new Farm(1, user, "Bob's Mega Farm");

        assertEquals(1, farm.getId());
        assertEquals(user.getAdriaId(), farm.getUserId());
        assertEquals("Bob's Mega Farm", farm.getName());
    }

    @Test
    void testEqualsWithNull() {
        Farm farm = new Farm(1, UUID.randomUUID(), "Bob's Mega Farm");

        assertNotEquals(null, farm);
    }

    @Test
    void testEqualsWithDifferentClass() {
        Farm farm = new Farm(1, UUID.randomUUID(), "Bob's Mega Farm");

        String differentClassObject = "Not a Farm";

        assertNotEquals(differentClassObject, farm);
    }

    @Test
    void testHashCodeWithSameId() {
        UUID userId = UUID.randomUUID();
        Farm farm1 = new Farm(1, userId, "Bob's Mega Farm");
        Farm farm2 = new Farm(1, userId, "Bob's Giga Farm");

        assertEquals(farm1.hashCode(), farm2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentIds() {
        UUID userId = UUID.randomUUID();
        Farm farm1 = new Farm(1, userId, "Bob's Mega Farm");
        Farm farm2 = new Farm(2, userId, "Bob's Mega Farm");

        assertNotEquals(farm1.hashCode(), farm2.hashCode());
    }
}
