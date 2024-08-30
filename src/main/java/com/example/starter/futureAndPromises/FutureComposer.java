package com.example.starter.futureAndPromises;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class FutureComposer
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();
    createHttpServer(vertx);
  }
  public static void createHttpServer(Vertx vertx)
  {
    vertx.createHttpServer().requestHandler(handler->{
        handler.response().
          setStatusCode(200)
          .putHeader("Message","Got request").
          end("<h1>Hello From Vertext</h1>");
      })
      .listen(44_44).
      compose(httpServer -> {
        System.out.println("First Compose "+httpServer.actualPort());
        return Future.succeededFuture(httpServer);
      }).
      compose(httpServer -> {
        System.out.println("Second Compose "+httpServer.actualPort());
        return Future.succeededFuture(httpServer);
      }).onComplete(event -> System.out.println("Server Completed"))
      .onSuccess(event-> System.out.println("Server Successd"));

  }
}
