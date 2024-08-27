package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;

public class TestVerticle extends AbstractVerticle
{

  public void start()
  {
    System.out.println("Verticle Deployed "+Thread.currentThread().getName());
    vertx.undeploy(vertx.getOrCreateContext().deploymentID());
  }
  public void stop()
  {
    System.out.println("Verticle Destroyed!");
  }
}
