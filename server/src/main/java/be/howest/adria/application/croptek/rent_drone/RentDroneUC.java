package be.howest.adria.application.croptek.rent_drone;

import be.howest.adria.application.contracts.repositories.croptek.DronesRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;
import be.howest.adria.domain.Drone;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Optional;

public class RentDroneUC implements UseCase<RentDroneInput> {

    private final OutputPort<HttpResponseStatus> outputPort;
    private final DronesRepo dronesRepo;

    public RentDroneUC(OutputPort<HttpResponseStatus> outputPort, DronesRepo dronesRepo) {
        this.outputPort = outputPort;
        this.dronesRepo = dronesRepo;
    }

    @Override
    public void execute(RentDroneInput input) {
        Optional<Drone> optionalDrone = dronesRepo.getAvailableDrones();

        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();
            drone.setUserId(Optional.of(input.getAdriaId()));
            dronesRepo.update(drone);
            outputPort.present(HttpResponseStatus.NO_CONTENT);
        } else {
            outputPort.present(HttpResponseStatus.SERVICE_UNAVAILABLE);
        }
    }
}