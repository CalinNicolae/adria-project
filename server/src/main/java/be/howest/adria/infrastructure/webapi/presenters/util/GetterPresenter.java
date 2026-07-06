package be.howest.adria.infrastructure.webapi.presenters.util;

import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.infrastructure.webapi.responsehandlers.util.GetterResponseHandler;
import io.vertx.ext.web.RoutingContext;

public class GetterPresenter<T> implements OutputPort<T> {
    private final GetterResponseHandler<T> responseHandler;
    public GetterPresenter(RoutingContext ctx) {
        this.responseHandler = new GetterResponseHandler<>(ctx);
    }
    @Override
    public void present(T data) {
        responseHandler.handle(data);
    }
}
