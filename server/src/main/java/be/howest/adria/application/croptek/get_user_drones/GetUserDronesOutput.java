package be.howest.adria.application.croptek.get_user_drones;

import be.howest.adria.domain.Drone;

import java.util.HashSet;
import java.util.Set;

public class GetUserDronesOutput extends HashSet<Drone> {

    public GetUserDronesOutput(Set<Drone> output){
        super(output);
    }

}
