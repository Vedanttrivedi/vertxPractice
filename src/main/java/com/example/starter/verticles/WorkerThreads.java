package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class WorkerThreads extends AbstractVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerThreads());
    //one way to deploy worker thread is to use execute blocking method
    //another way is

    vertx.deployVerticle(
      new WorkerThreads(),
      new DeploymentOptions().setWorkerPoolName("MyWorkerThread").setWorker(true));
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    startPromise.complete();
    vertx.executeBlocking(event -> {
      System.out.println("Inside the blocking code " + Thread.currentThread().getName());

      try {
        Thread.sleep(4000);
        event.complete();
        //event.fail()
      } catch (InterruptedException e) {
        System.out.println(e);
      }
    }, result -> {
      //this code will onyl executed when blocking event completes
      //blocking event is executed on worker thread
      //while result is executed on eventpool thread
      System.out.println("Result " + result.result());
    });
  }
}
