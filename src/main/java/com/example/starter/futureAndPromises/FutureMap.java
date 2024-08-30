package com.example.starter.futureAndPromises;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class FutureMap
{
  public static void main(String[] args) {

    var vertx = Vertx.vertx();

    Promise<String> promise = Promise.promise();
    vertx.setTimer(4000,event->promise.complete("4 seconds finished"));
    Future<String> future = promise.future();

    //now if we want to transform the value future ,we can use map
    //to change that data type to other data type

    future.
      map(string-> new JsonObject().put("Key1",string)).//here we are transforming string into jsonobject
      onSuccess(event -> System.out.println("Future Success "+event)).
      onComplete(event -> System.out.println("Future Completed "+event.result()));

  }
}
