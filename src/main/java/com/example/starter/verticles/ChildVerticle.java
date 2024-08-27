package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;

public class ChildVerticle extends AbstractVerticle {
  public void start()
  {
    System.out.println("Child Verticle Deployed!! "+Thread.currentThread().getName());
  }
  public void stop(){
    System.out.println("Verticle Destroyed");
  }
}
