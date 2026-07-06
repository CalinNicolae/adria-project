package be.howest.adria.infrastructure.webapi.shared;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.ext.web.RoutingContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class ApiResult {
    private static final Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(Optional.class, new OptionalTypeAdapter<>())
                .create();
    public static void created(RoutingContext ctx, String location) { //NOSONAR: Mr. Blomme provided this code
        sendStatusCode(HttpResponseStatus.CREATED, ctx);
    }
    
    public static void problemDetails(RoutingContext ctx, ProblemDetails problemDetails) {
        ctx.response()
                .setStatusCode(problemDetails.getStatus())
                .putHeader("Content-Type", "application/problem+json")
                .end(gson.toJson(problemDetails));
    }

    public static void ok(RoutingContext ctx) {
        sendStatusCode(HttpResponseStatus.OK, ctx);
    }

    public static void ok(RoutingContext ctx, Object body) {
        ctx.response()
                .putHeader("Content-Type", "application/json")
                .setStatusCode(HttpResponseStatus.OK.code())
                .end(gson.toJson(body));
    }

    public static void noContent(RoutingContext ctx) {
        sendStatusCode(HttpResponseStatus.NO_CONTENT, ctx);
    }

    public static void serviceUnavailable(RoutingContext ctx) {
        sendStatusCode(HttpResponseStatus.SERVICE_UNAVAILABLE, ctx);
    }

    public static void sendStatusCode(HttpResponseStatus status, RoutingContext ctx) {
        ctx.response()
                .setStatusCode(status.code())
                .end();
    }

    private ApiResult() {
    }

}
