package be.howest.adria.infrastructure.webapi.responsehandlers.util;

import be.howest.adria.infrastructure.webapi.shared.ApiResult;
import be.howest.adria.infrastructure.webapi.shared.ResponseHandler;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.ext.web.RoutingContext;

public class ResponseHandlerWithStatus implements ResponseHandler<HttpResponseStatus> {
    private final RoutingContext ctx;

    public ResponseHandlerWithStatus(RoutingContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void handle(HttpResponseStatus response) {
        ApiResult.sendStatusCode(response, ctx);
    }

}
