package be.howest.adria.domain;

import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID adriaId;
    private String fullName;

    public User(UUID adriaId, String fullName) {
        this.adriaId = Objects.requireNonNull(adriaId);
        this.fullName= Objects.requireNonNull(fullName);
    }

    public UUID getAdriaId() {
        return adriaId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = Objects.requireNonNull(fullName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return adriaId.equals(user.adriaId);
    }

    @Override
    public int hashCode() {
        return adriaId.hashCode();
    }

}
