package be.howest.adria.domain;

import java.util.Objects;

public class FarmField {

    private final int id;
    private final int farmId;
    private final String name;

    public FarmField(int id, int farmId, String name){
        this.id = id;
        this.farmId = farmId;
        this.name = Objects.requireNonNull(name);
    }

    public FarmField(int id, Farm farm, String name){
        this(id, farm.getId(), name);
    }

    public int getId() {
        return id;
    }

    public int getFarmId(){
        return farmId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FarmField farmField = (FarmField) o;
        return id == farmField.id && farmId == farmField.farmId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + farmId;
        return result;
    }

}
