package com.example.starter.cutsomObjectSender;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

import java.util.concurrent.TimeUnit;

public class SenderVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    System.out.println("Sender Verticle Loaded " + Thread.currentThread().getName());

    var eventBus = vertx.eventBus();

    eventBus.request("ADDRESS", new Pojo("Vedant", "12345").toJson(),
      response -> {
        System.out.println("In Sender Verticle " + Thread.currentThread().getName());
        System.out.println("Response " + response.result().body());
      });

    System.out.println("Hello " + Thread.currentThread().getName());
    //Thread.sleep(500000);
    for (long i = 1; i < 10_000000000000l; i++) {
     //System.out.println("Pringint " + i);
      var x = i;
    }

    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    System.out.println("Verticle is undeployed");

    stopPromise.complete();
  }
}
