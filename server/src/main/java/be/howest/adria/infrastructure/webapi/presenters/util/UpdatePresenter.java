package be.howest.adria.infrastructure.webapi.presenters.util;

import be.howest.adria.application.contracts.usecases.OutputPort;
import be.howest.adria.infrastructure.webapi.responsehandlers.util.ResponseHandlerWithStatus;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.ext.web.RoutingContext;

public class UpdatePresenter implements OutputPort<HttpResponseStatus> {
    private final ResponseHandlerWithStatus responseHandler;
    public UpdatePresenter(RoutingContext ctx) {
        this.responseHandler = new ResponseHandlerWithStatus(ctx);
    }
    @Override
    public void present(HttpResponseStatus data) {
        responseHandler.handle(data);
    }

}
