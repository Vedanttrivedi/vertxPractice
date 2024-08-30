package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;

import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class TestVM
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();
    System.out.println("Running on..."+Thread.currentThread().getName());
    for (int i = 1; i <=10 ; i++)
    {
      vertx.deployVerticle(new Tester(),new DeploymentOptions().setThreadingModel(ThreadingModel.EVENT_LOOP));
    }
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter Details in main Thread: ");
    var value = scan.nextInt();
  }
}


class Tester extends AbstractVerticle
{
  @Override
  public void start() throws Exception
  {
    System.out.println("Threadsssss "+Thread.currentThread().getName());
    System.out.println("Verticle Loaded");

    Scanner scan = new Scanner(System.in);
    System.out.println("Enter number : ");
    var val = scan.nextInt();
  }
}
