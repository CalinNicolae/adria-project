package be.howest.adria.application.croptek.get_user_notifications;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;

public class GetUserNotificationsUC implements UseCase<GetUserNotificationsInput> {

    private final OutputPort<GetUserNotificationsOutput> outputPort;
    private final NotificationsRepo notificationsRepo;

    public GetUserNotificationsUC(OutputPort<GetUserNotificationsOutput> outputPort, NotificationsRepo notificationsRepo) {
        this.outputPort = outputPort;
        this.notificationsRepo = notificationsRepo;
    }

    @Override
    public void execute(GetUserNotificationsInput input) {
        outputPort.present(
                new GetUserNotificationsOutput(
                        notificationsRepo.getNotifications(input.getUserAdriaId())
                )
        );
    }

}
