package com.example.starter.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class sendJsonObject
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new ConsumerVerticle());

    vertx.deployVerticle(new ProducerVerticle());
  }
  public static class ProducerVerticle extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
      super.start(startPromise);

      vertx.eventBus().publish("12345",new JsonObject().put("Name","Vedant"));
      System.out.println("data sent from "+Thread.currentThread().getName());
    }
  }
  public static class ConsumerVerticle extends AbstractVerticle
  {
    @Override
    public void start() throws Exception
    {
      System.out.println("Listening");
      vertx.eventBus().consumer("12345",handler->{
        System.out.println("Recieved "+handler.body());

      });
    }
  }
}
