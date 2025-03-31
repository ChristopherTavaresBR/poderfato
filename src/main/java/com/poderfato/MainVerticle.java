package com.poderfato;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/hello").handler(ctx -> ctx.response()
            .putHeader("content-type", "text/plain")
            .end("Olá, mundo!"));

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8080, http -> {
                if (http.succeeded()) {
                    startPromise.complete();
                    System.out.println("Servidor rodando em http://localhost:8080");
                } else {
                    startPromise.fail(http.cause());
                }
            });
    }
}
