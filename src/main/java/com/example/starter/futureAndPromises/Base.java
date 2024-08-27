package com.example.starter.futureAndPromises;

import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class Base
{
  public static void main(String[] args)
  {
      var vertx = Vertx.vertx();
      vertx.deployVerticle(new Tester());
  }
  static class Tester extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {

      final Promise promise = Promise.promise();
      vertx.setTimer(5000,handler->{
        promise.fail("Failed");
      });

      final Future future = promise.future();
      future.
        onSuccess(handler->{
        System.out.println("Promise Successed!");
      }).
        onComplete(handler->{
          System.out.println("Promise Completed");
        }).
        onFailure(handler->{
          System.out.println("Promise Failed");
        });
    }
  }
}
