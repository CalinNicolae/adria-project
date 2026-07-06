package be.howest.adria.application.croptek.get_all_farms;

import be.howest.adria.domain.Farm;
import java.util.HashSet;
import java.util.Set;

public class GetAllFarmsOutput extends HashSet<Farm> {

    public GetAllFarmsOutput(Set<Farm> output) {
        super(output);
    }

}
