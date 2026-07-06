package be.howest.adria.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Drone {

    private final UUID id;
    private Optional<UUID> userId;
    private final LocalDate manufacturingDate;
    private boolean functional;
    private boolean needsRepair;
    private int batteryLevel;
    private DroneActivity activity;

    public Drone(UUID id,
                 LocalDate manufacturingDate,
                 boolean functional, boolean needsRepair,
                 int batteryLevel, DroneActivity activity, Optional<UUID> userId) {
        this.id = Objects.requireNonNull(id);
        this.manufacturingDate = Objects.requireNonNull(manufacturingDate);
        this.functional = functional;
        this.needsRepair = needsRepair;
        this.batteryLevel = batteryLevel;
        this.activity = Objects.requireNonNull(activity);
        this.userId = userId;
    }

    public Drone(UUID id){
       this(id, LocalDate.now(), true, false, 100, DroneActivity.PASSIVE, Optional.empty());
    }

    public Drone(UUID id, LocalDate manufacturingDate, boolean functional, boolean needsRepair, int batteryLevel, DroneActivity activity, User user){
        this(id, manufacturingDate, functional, needsRepair, batteryLevel, activity, Optional.of(user.getAdriaId()));
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public boolean isFunctional() {
        return functional;
    }

    public void setFunctional(boolean functional) {
        this.functional = functional;
    }

    public boolean needsRepair() {
        return needsRepair;
    }

    public void setNeedsRepair(boolean needsRepair) {
        this.needsRepair = needsRepair;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public DroneActivity getActivity() {
        return activity;
    }

    public void setActivity(DroneActivity activity) {
        this.activity = activity;
    }

    public Optional<UUID> getUserId(){
        return userId;
    }

    public void setUserId(Optional<UUID> userId){
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Drone drone = (Drone) o;
        return id.equals(drone.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
