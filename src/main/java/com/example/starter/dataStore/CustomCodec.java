package com.example.starter.dataStore;

import com.example.starter.eventBus.ProducerConsumer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageCodec;

public class CustomCodec
{
  public static void main(String[] args)
  {
       var vertx = Vertx.vertx();
//      vertx.deployVerticle(
//      new ProducerConsumer.Consumer2Verticle(),
//      error->{
//        System.out.println("Verticle finised");
//        System.out.println(error.result().toString());
//      });
//
    vertx.deployVerticle(new YourCustomVerticle());
    vertx.deployVerticle(new MyCustomVerticle());
  }
  static class MyCustomVerticle extends AbstractVerticle
  {
    @Override
    public void start() throws Exception {
      System.out.println("Deployed 1");
//      vertx.eventBus().registerDefaultCodec(Person.class, new MessageCodec<Person, Object>() {
//
//      });
      vertx.eventBus().publish("123456",new Person("Vednat",22));
      System.out.println("Sent");
    }
  }
  static class YourCustomVerticle extends AbstractVerticle
  {
    @Override
    public void start() throws Exception {
      System.out.println("Deployed 2");
      //vertx.eventBus().registerDefaultCodec(Person.class,)
      vertx.eventBus().consumer("123456",handler->{
        System.out.println("Recieved "+handler.body() );
        System.out.println("Recived");
      });
    }
  }
}
