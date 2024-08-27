package com.example.starter.eventBus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
public class PointToPoint
{
  static final String address = "Server.CLASS";
  public static void main(String[] args)
  {
      var vertx = Vertx.vertx();
      vertx.deployVerticle(new ServerVerticle());
      vertx.deployVerticle(new SenderVerticle());
  }
  //In point to point message is sent to desired entity but that entity does not return the
  //response.
  static class SenderVerticle extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
      startPromise.complete();
      vertx.setPeriodic(1000,event -> vertx.eventBus().send(address,"Sending Message to Server"));
    }
  }

  static class ServerVerticle extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
      //startPromise.complete();

      vertx.eventBus().<String>consumer(address,handler->
      {
        System.out.println("Body "+handler.body());
      });
    }
  }
}
