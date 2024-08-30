package com.example.starter.futureAndPromises;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/*
Coordination of multiple futures can be achieved with Vert.x
futures
. It supports concurrent composition
 (run several async operations in parallel) and sequential composition (chain async operations).*/


public class FutureCoordination
{
  public static void main(String[] args)
  {
      var vertx = Vertx.vertx();
      vertx.deployVerticle(new Verticlers());
  }
  static public class Verticlers extends AbstractVerticle
  {
    @Override
    public void start() throws Exception {
      System.out.println("Verticle loaded");
      Future httpServer = vertx.createHttpServer().
        requestHandler(request -> {
          request.response().end("<B>Got the requet</B>");
        }).listen(4013);

      Future netServer = vertx.createNetServer().listen();

      System.out.println("Runnning on Thread " + Thread.currentThread().getName());
      Future.any(httpServer, netServer)
        .onComplete(handler ->
        {
          if (handler.succeeded()) {
            System.out.println("All Thre Futures are succesded");
          } else {
            System.out.println("One Of the future failed");
          }

        });

      vertx.executeBlocking(event -> {
        System.out.println("Executing block thread " + Thread.currentThread().getName());
      });
    }
  }
}
