package com.example.starter;

import io.vertx.core.AbstractVerticle;

public class AnotherVerticle extends AbstractVerticle {

  @Override
  public void start(){

    System.out.println("Verticle Deployed!!! in thread "+Thread.currentThread().getName());
  }

}
