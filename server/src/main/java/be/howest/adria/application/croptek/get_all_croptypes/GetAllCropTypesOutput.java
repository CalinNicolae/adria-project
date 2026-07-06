package be.howest.adria.application.croptek.get_all_croptypes;

import be.howest.adria.domain.CropType;

import java.util.HashSet;
import java.util.Set;

public class GetAllCropTypesOutput extends HashSet<CropType> {

    public GetAllCropTypesOutput(Set<CropType> output){
        super(output);
    }

}
