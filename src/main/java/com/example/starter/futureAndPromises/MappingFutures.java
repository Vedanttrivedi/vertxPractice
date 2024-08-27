package com.example.starter.futureAndPromises;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class MappingFutures
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new BaseVerticle());
  }
  static class BaseVerticle extends AbstractVerticle
  {
    @Override
    public void start() throws Exception
    {
        Promise<String>promise = Promise.promise();
        vertx.setTimer(4000,event -> {
          System.out.println("Completing Promise");
          promise.complete("Done!");

        });

      Future<String>future = promise.future();
      future.
        map(string-> new JsonObject().put("Key",string)).
        onComplete(handler->{
          System.out.println("promise completed");
          System.out.println(future.result() +"\t"+handler.result());
      });
    }
  }
}
