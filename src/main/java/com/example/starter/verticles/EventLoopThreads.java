package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.concurrent.TimeUnit;

public class EventLoopThreads extends AbstractVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx(
      new VertxOptions().
        setEventLoopPoolSize(4).
        setMaxEventLoopExecuteTime(100).
        setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS).
        setBlockedThreadCheckInterval(1).
        setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
    );
    vertx.deployVerticle(new EventLoopThreads());
    vertx.deployVerticle(new EventLoopThreads());
    vertx.deployVerticle(new EventLoopThreads());
    vertx.deployVerticle(new EventLoopThreads());
    vertx.deployVerticle(new EventLoopThreads());
    vertx.deployVerticle(new EventLoopThreads());
    vertx.deployVerticle(new EventLoopThreads());

  }
  @Override
  public void start() throws Exception
  {
    System.out.println("Event Loop Thread Will block "+Thread.currentThread().getName());
    Thread.sleep(15000);
  }

}
