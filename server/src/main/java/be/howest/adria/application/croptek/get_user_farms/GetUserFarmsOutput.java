package be.howest.adria.application.croptek.get_user_farms;

import be.howest.adria.domain.Farm;
import java.util.HashSet;
import java.util.Set;

public class GetUserFarmsOutput extends HashSet<Farm> {

    public GetUserFarmsOutput(Set<Farm> output) {
        super(output);
    }

}
