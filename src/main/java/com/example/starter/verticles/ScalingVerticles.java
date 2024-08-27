package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class ScalingVerticles
{
  public static void main(String[] args) throws Exception
  {
    var vertx = Vertx.vertx();
   vertx.deployVerticle(
      TestVerticle.class.getName()
     ,new DeploymentOptions().setInstances(4)
    );
    System.out.println("Main Program "+TestVerticle.class.getName());
//    for (int i = 0; i < 4; i++) {
//      vertx.deployVerticle(new TestVertical());
//    }
  }
}
