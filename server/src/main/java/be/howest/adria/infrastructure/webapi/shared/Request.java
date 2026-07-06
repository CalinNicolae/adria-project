package be.howest.adria.infrastructure.webapi.shared;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.openapi.validation.RequestParameter;
import io.vertx.openapi.validation.ValidatedRequest;
import java.util.Map;


public class Request {
    private final ValidatedRequest validatedRequest;
    private final RoutingContext ctx;

    public Request(RoutingContext ctx) {
        // MBL: I cannot find a vertx constant for this key, so I'm using the string
        validatedRequest = ctx.get("openApiValidatedRequest");
        this.ctx = ctx;
    }

    public JsonObject body() {
        return validatedRequest.getBody().getJsonObject();
    }

    public String pathParam(String key) {
        // Direct access from RoutingContext if validatedRequest is unreliable
        return getCtx().pathParam(key);
    }

    public Map<String, RequestParameter> queryParam() {
        return validatedRequest.getQuery();
    }
    public RoutingContext getCtx() {
        return ctx;
    }
}
