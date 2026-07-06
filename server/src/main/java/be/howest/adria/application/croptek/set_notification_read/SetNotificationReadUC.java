package be.howest.adria.application.croptek.set_notification_read;

import be.howest.adria.application.contracts.repositories.croptek.NotificationsRepo;
import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.application.contracts.usecases.UseCase;
import be.howest.adria.domain.Notification;
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.Objects;
import java.util.Optional;

public class SetNotificationReadUC implements UseCase<SetNotificationReadInput> {

    private final OutputPort<HttpResponseStatus> outputPort;
    private final NotificationsRepo notificationsRepo;

    public SetNotificationReadUC(OutputPort<HttpResponseStatus> outputPort, NotificationsRepo notificationsRepo) {
        this.outputPort = Objects.requireNonNull(outputPort);
        this.notificationsRepo = Objects.requireNonNull(notificationsRepo);
    }

    public void execute(SetNotificationReadInput input) {
        final Optional<Notification> notificationOptional = notificationsRepo.getNotificationById(input.getNotificationId());

        if(notificationOptional.isPresent()) {
            final Notification notification = notificationOptional.get();

            notification.setRead(input.isRead());

            notificationsRepo.update(notification);
            outputPort.present(HttpResponseStatus.NO_CONTENT);
        }else{
            outputPort.present(HttpResponseStatus.NOT_FOUND);
        }
    }

}
