package be.howest.adria.application.croptek.get_planted_crops;

public class GetPlantedCropsInput {

    private final int farmId;
    private final int farmFieldId;

    public GetPlantedCropsInput(int farmId, int farmFieldId) {
        this.farmId = farmId;
        this.farmFieldId = farmFieldId;
    }

    public int getFarmId() {
        return farmId;
    }

    public int getFarmFieldId() {
        return farmFieldId;
    }

}
