package be.howest.adria.domain;

import java.util.Objects;

public class CropType {

    private final int id;
    private String name;
    private int minGrowthDays;
    private int maxGrowthDays;

    public CropType(int id, String name, int minGrowthDays, int maxGrowthDays) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.minGrowthDays = minGrowthDays;
        this.maxGrowthDays = maxGrowthDays;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public int getMinGrowthDays() {
        return minGrowthDays;
    }

    public void setMinGrowthDays(int minGrowthDays) {
        this.minGrowthDays = minGrowthDays;
    }

    public int getMaxGrowthDays() {
        return maxGrowthDays;
    }

    public void setMaxGrowthDays(int maxGrowthDays) {
        this.maxGrowthDays = maxGrowthDays;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CropType cropType = (CropType) o;
        return id == cropType.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
