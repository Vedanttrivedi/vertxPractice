package com.example.starter;

import io.vertx.core.*;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HelloVerticle {
  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    JsonObject jsonObject = new JsonObject();
    System.out.println("Map  "+jsonObject.getMap().getClass());
    vertx.deployVerticle(new VerticleDeploy(),
      new DeploymentOptions().setConfig(new JsonObject().put("1",1))
    ).onComplete(event -> System.out.println("Verticle Finished"));
  }
  static public class VerticleDeploy extends AbstractVerticle
  {
    static String address="Verticle.class";
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.getOrCreateContext().put("12",12);

      //System.out.println("Context Data "+vertx.getOrCreateContext().get("12"));
      System.out.println("Context Data "+vertx.getOrCreateContext().config());
      System.out.println("Deployed Completed");

      //Vertext Consumer returns consumer
      MessageConsumer<String> consumer = vertx.eventBus().localConsumer("address");
      consumer.handler(message -> {
        System.out.println("Received " + message.body());
      });

// Correcting the creation of the ArrayList and sending it properly
      List<Integer> list = new ArrayList<>();
      list.add(123);

      vertx.eventBus().request("address", list, whenArrives -> {
        if (whenArrives.succeeded()) {
          System.out.println("Request Completed: " + whenArrives.result().body());
        } else {
          System.out.println("Request Failed: " + whenArrives.cause());
        }
      });

      try{
        Thread.sleep(4000);
      }
      catch (InterruptedException e)
      {
        System.out.println(e.getMessage());
      }
    }
  }
}
