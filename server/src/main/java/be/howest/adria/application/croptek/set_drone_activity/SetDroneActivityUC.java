package be.howest.adria.application.croptek.set_drone_activity;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;
import be.howest.adria.domain.Drone;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Optional;

public class SetDroneActivityUC implements UseCase<SetDroneActivityInput> {

    private final OutputPort<HttpResponseStatus> outputPort;
    private final DronesRepo dronesRepo;

    public SetDroneActivityUC(OutputPort<HttpResponseStatus> outputPort, DronesRepo dronesRepo) {
        this.outputPort = outputPort;
        this.dronesRepo = dronesRepo;
    }

    public void execute(SetDroneActivityInput input) {
        final Optional<Drone> droneOptional = dronesRepo.getDrone(input.getDroneId());

        if(droneOptional.isPresent()) {
            final Drone drone = droneOptional.get();

            drone.setActivity(input.getActivity());

            dronesRepo.update(drone);
            outputPort.present(HttpResponseStatus.NO_CONTENT);
        }else{
            outputPort.present(HttpResponseStatus.NOT_FOUND);
        }
    }

}
