package be.howest.adria.domain;

import java.util.Objects;
import java.util.UUID;

public class Farm {

    private final int id;
    private final UUID userId;
    private String name;


    public Farm(int id, UUID userId, String name) {
        this.id = id;
        this.userId = Objects.requireNonNull(userId);
        this.name = Objects.requireNonNull(name);
    }

    public Farm(int id, User user, String name){
        this(id, user.getAdriaId(), name);
    }

    public int getId() {
        return id;
    }

    public UUID getUserId(){
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Farm farm = (Farm) o;
        return id == farm.id && userId.equals(farm.userId);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId.hashCode();
        return result;
    }

}
