package be.howest.adria.application.croptek.return_drone;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;
import be.howest.adria.domain.Drone;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Optional;

public class ReturnDroneUC implements UseCase<ReturnDroneInput> {

    private final OutputPort<HttpResponseStatus> outputPort;
    private final DronesRepo dronesRepo;

    public ReturnDroneUC(OutputPort<HttpResponseStatus> outputPort, DronesRepo dronesRepo) {
        this.outputPort = outputPort;
        this.dronesRepo = dronesRepo;
    }

    @Override
    public void execute(ReturnDroneInput input) {
        final Optional<Drone> drone = dronesRepo.getDrone(input.getDroneId());

        if(drone.isPresent()) {
            drone.get().setUserId(Optional.empty());
            dronesRepo.update(drone.get());
            outputPort.present(HttpResponseStatus.NO_CONTENT);
        }else{
            outputPort.present(HttpResponseStatus.NOT_FOUND);
        }
    }
}