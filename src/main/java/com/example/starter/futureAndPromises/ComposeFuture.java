package com.example.starter.futureAndPromises;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class ComposeFuture
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();
  }
  static class composerFuture extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
      super.start(startPromise);
      vertx.createHttpServer().
        requestHandler(req-> System.out.println("Server is started"))
        .listen(10000)
        .compose(server->{
          System.out.println("Future Composed!");
          return Future.succeededFuture();
        })
        .onSuccess(handler->{
          System.out.println("Server listening");
        });
    }
  }
}
