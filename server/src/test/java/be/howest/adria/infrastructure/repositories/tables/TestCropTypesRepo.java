package be.howest.adria.infrastructure.repositories.tables;

import be.howest.adria.application.contracts.repositories.croptek.CropTypesRepo;
import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.CropType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TestCropTypesRepo implements CropTypesRepo {

    private final Set<CropType> cropTypes = new HashSet<>();

    @Override
    public CropType getCropType(int id) throws RepoAccessException {
        final Optional<CropType> cropTypeFound = getCropTypeOptional(id);

        if(cropTypeFound.isPresent()) {
            return cropTypeFound.get();
        }else{
            throw new RepoAccessException("Crop type not found");
        }
    }

    @Override
    public Set<CropType> getAllCropTypes() {
        return Collections.unmodifiableSet(cropTypes);
    }

    @Override
    public void addCropType(CropType cropType) throws RepoAccessException {
        cropTypes.add(cropType);
    }

    private Optional<CropType> getCropTypeOptional(int cropTypeId) {
        for (CropType cropType : cropTypes) {
            if (cropType.getId() == cropTypeId) {
                return Optional.of(cropType);
            }
        }

        return Optional.empty();
    }

}
