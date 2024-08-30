package com.example.starter.eventBus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer
{
  static final String address = "ABCDE";
  public static void main(String[] args)
  {
    var value = 10;
    var vertex = Vertx.vertx();
    vertex.deployVerticle(
      ProduceVerticle.class.getName(),
      new DeploymentOptions().setInstances(1000)
    );
    vertex.deployVerticle(new ProduceVerticle());
  }
  public static class ProduceVerticle extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
      startPromise.complete();
      JsonObject data = new JsonObject();
      data.put("Name","Vedant");
      System.out.println("Thread "+Thread.currentThread().getName());
//
//      vertx.setPeriodic(Duration.ofSeconds(10).toMillis(),id->{
//        vertx.eventBus().publish(address,"This is data!!");
//      });
//      System.out.println("Producer Thread "+Thread.currentThread().getName());
    }
  }

  public static class ConsumerVerticle extends AbstractVerticle
  {
    @Override
    public void start() throws Exception
    {

      super.start();
      vertx.eventBus().consumer(address,handler->
      {
        System.out.println("Handler recieved "+handler.body() +" Thread "+Thread.currentThread().getName());
        System.out.println("I am taking data in consumer "+config().getString("Name"));
      });
    }
  }

  public static class Consumer2Verticle extends AbstractVerticle
  {
    @Override
    public void start()
    {
      vertx.eventBus().consumer(address,handler->{
        System.out.println("Message Recieved by consumer 2"+handler.body()+" Thread "+Thread.currentThread().getName());
      });
    }
  }
}

