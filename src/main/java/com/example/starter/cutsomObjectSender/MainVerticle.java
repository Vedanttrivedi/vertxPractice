package com.example.starter.cutsomObjectSender;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.concurrent.TimeUnit;

public class MainVerticle {
  public static void main(String[] args) {
    var vertx = Vertx.vertx();

    System.out.println("Main Application " + Thread.currentThread().getName());

    vertx.deployVerticle(new RecieverVerticle());

    vertx.deployVerticle(new SenderVerticle());

    System.out.println("Main Thread Over ");
  }
}
