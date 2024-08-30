package com.example.starter.cutsomObjectSender;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class RecieverVerticle extends AbstractVerticle {


  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Reciever Verticle Loaded " + Thread.currentThread().getName());

    vertx.eventBus().localConsumer("ADDRESS", handler -> {
      System.out.println("inside rec : " + handler.body());
    });

    startPromise.complete();
  }
}
