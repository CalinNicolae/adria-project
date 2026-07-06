package be.howest.adria.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserTest {

    @Test
    void testCreateUser(){
        UUID adriaId = UUID.randomUUID();
        String fullName = "Juan Peron";

        User user = new User(adriaId, fullName);

        assertEquals(adriaId, user.getAdriaId());
        assertEquals(fullName, user.getFullName());
    }

    @Test
    void testSetFullName() {
        UUID adriaId = UUID.randomUUID();
        User user = new User(adriaId, "John Doe");

        user.setFullName("Jane Smith");

        assertEquals("Jane Smith", user.getFullName());
    }

    @Test
    void testUserEquality(){
        UUID adriaId = UUID.randomUUID();
        String fullName = "Juan Peron";

        User user1 = new User(adriaId, fullName);
        User user2 = new User(adriaId, fullName);

        assertEquals(user1, user2);
    }

    @Test
    void testUserEqualityIsNotAlwaysTrue(){
        UUID adriaId1 = UUID.fromString("50b1871f-e600-4791-9566-787b029854b7");
        String fullName1 = "Juan Peron";

        UUID adriaId2 = UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b2");
        String fullName2 = "Johnny Guitar";

        User user1 = new User(adriaId1, fullName1);
        User user2 = new User(adriaId2, fullName2);

        assertNotEquals(user1, user2);
    }

    @Test
    void testSetFullNameWithNull() {
        UUID adriaId = UUID.randomUUID();
        User user = new User(adriaId, "John Doe");

        assertThrows(NullPointerException.class, () -> user.setFullName(null));
    }

    @Test
    void testEqualsWithNull() {
        UUID adriaId = UUID.randomUUID();
        User user = new User(adriaId, "John Doe");

        assertNotEquals(null, user);
    }

    @Test
    void testEqualsWithDifferentClass() {
        UUID adriaId = UUID.randomUUID();
        User user = new User(adriaId, "John Doe");

        String differentClassObject = "Not a User";

        assertNotEquals(differentClassObject, user);
    }

    @Test
    void testEqualsWithSameId() {
        UUID adriaId = UUID.randomUUID();
        User user1 = new User(adriaId, "John Doe");
        User user2 = new User(adriaId, "Jane Smith");

        assertEquals(user1, user2);
    }

    @Test
    void testEqualsWithDifferentId() {
        User user1 = new User(UUID.randomUUID(), "John Doe");
        User user2 = new User(UUID.randomUUID(), "Jane Smith");

        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCodeWithSameId() {
        UUID adriaId = UUID.randomUUID();
        User user1 = new User(adriaId, "John Doe");
        User user2 = new User(adriaId, "Jane Smith");

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentId() {
        User user1 = new User(UUID.randomUUID(), "John Doe");
        User user2 = new User(UUID.randomUUID(), "Jane Smith");

        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

}
