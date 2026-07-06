package be.howest.adria.application.contracts.repositories.croptek;

import be.howest.adria.application.exceptions.RepoAccessException;
import be.howest.adria.domain.CropType;

import java.util.Set;

public interface CropTypesRepo {

    CropType getCropType(int id) throws RepoAccessException;

    Set<CropType> getAllCropTypes();

    void addCropType(CropType cropType) throws RepoAccessException;

}
