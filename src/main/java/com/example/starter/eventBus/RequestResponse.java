package com.example.starter.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class RequestResponse
{
  static final String address = "Response.class";
  public static void main(String[] args)
  {
      var vertx = Vertx.vertx();
      vertx.deployVerticle(new RequestVerticle());
      vertx.deployVerticle(new ResponseVerticle());
  }
  static class RequestVerticle extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      eventBus.request(address,"Message For Server",whenResponseArrives->
      {
        System.out.println("Server sent response "+whenResponseArrives.result().body());
      });
    }
  }

  static class ResponseVerticle extends AbstractVerticle
  {
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
//      startPromise.complete();
//      var vertx = Vertx.vertx();
      var eventBus = vertx.eventBus();
      eventBus.consumer(address,handler->
      {
        System.out.println("Client sent "+handler.body());
        handler.reply("I Recieved your message");
      });
    }
  }
}


