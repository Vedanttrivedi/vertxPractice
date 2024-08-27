package com.example.starter;
import com.example.starter.verticles.ChildVerticle;
import io.vertx.core.Vertx;
public class MainRunner
{
  public static void main(String[] args)
  {
    var vertex = Vertx.vertx();
    vertex.deployVerticle(new MainVerticle());

    vertex.deployVerticle(new ChildVerticle(),whenDeployed->{
      //vertex.undeploy(whenDeployed.result());
    });
    for (int i = 1; i <=5 ; i++)
    {
      vertex.deployVerticle(new AnotherVerticle());
    }
    System.out.println("Main Program "+Thread.currentThread().getName());
  }
}
