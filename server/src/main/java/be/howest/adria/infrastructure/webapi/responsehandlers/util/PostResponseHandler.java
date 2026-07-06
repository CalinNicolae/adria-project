package be.howest.adria.infrastructure.webapi.responsehandlers.util;

import be.howest.adria.infrastructure.webapi.shared.ApiResult;
import be.howest.adria.infrastructure.webapi.shared.ResponseHandler;
import io.vertx.ext.web.RoutingContext;

public class PostResponseHandler implements ResponseHandler<String> {

    private final RoutingContext ctx;

    public PostResponseHandler(RoutingContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void handle(String response) {
        ApiResult.created(ctx, response);
    }

}
