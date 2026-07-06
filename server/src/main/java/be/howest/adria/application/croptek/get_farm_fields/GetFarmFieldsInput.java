package be.howest.adria.application.croptek.get_farm_fields;

public class GetFarmFieldsInput {

    private final int farmId;

    public GetFarmFieldsInput(int farmId) {
        this.farmId = farmId;
    }

    public int getFarmId() {
        return farmId;
    }

}
