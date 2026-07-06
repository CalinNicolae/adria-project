package be.howest.adria.application.croptek.get_all_drones;

import be.howest.adria.domain.Drone;
import java.util.HashSet;
import java.util.Set;

public class GetAllDronesOutput extends HashSet<Drone> {

    public GetAllDronesOutput(Set<Drone> output) {
        super(output);
    }

}
