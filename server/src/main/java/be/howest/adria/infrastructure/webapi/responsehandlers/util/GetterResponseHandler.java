package be.howest.adria.infrastructure.webapi.responsehandlers.util;

import be.howest.adria.infrastructure.webapi.shared.ApiResult;
import be.howest.adria.infrastructure.webapi.shared.ResponseHandler;
import io.vertx.ext.web.RoutingContext;

public class GetterResponseHandler<T> implements ResponseHandler<T> {
    private final RoutingContext ctx;

    public GetterResponseHandler(RoutingContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void handle(T response) {
        ApiResult.ok(ctx, response);
    }
}
