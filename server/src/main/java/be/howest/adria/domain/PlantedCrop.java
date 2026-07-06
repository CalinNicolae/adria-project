package be.howest.adria.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public class PlantedCrop {

    private final int id;
    private final int typeId;
    private final int farmId;
    private final int farmFieldId;

    private Optional<Instant> harvest;
    private Instant planted;

    public PlantedCrop(int id, int typeId, int farmId, int farmFieldId, Instant planted, Optional<Instant> harvest) {
        this.id = id;
        this.typeId = typeId;
        this.farmId = farmId;
        this.farmFieldId = farmFieldId;
        this.harvest = harvest;
        this.planted = Objects.requireNonNull(planted);
    }

    public PlantedCrop(int id, CropType type, Farm farm, FarmField farmField, Instant planted, Optional<Instant> harvest) {
        this(id, type.getId(), farm.getId(), farmField.getId(), planted, harvest);
    }

    public int getId() {
        return id;
    }

    public Instant getPlanted() {
        return planted;
    }

    public Optional<Instant> getHarvest() {
        return harvest;
    }

    public void setHarvest(Optional<Instant> harvest) {
        this.harvest = harvest;
    }

    public void setPlanted(Instant planted) {
        this.planted = Objects.requireNonNull(planted);
    }

    public int getTypeId(){
        return typeId;
    }

    public int getFarmId() {
        return farmId;
    }

    public int getFarmFieldId() {
        return farmFieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PlantedCrop that = (PlantedCrop) o;
        return id == that.id && typeId == that.typeId && farmId == that.farmId && farmFieldId == that.farmFieldId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + typeId;
        result = 31 * result + farmId;
        result = 31 * result + farmFieldId;
        return result;
    }

}
