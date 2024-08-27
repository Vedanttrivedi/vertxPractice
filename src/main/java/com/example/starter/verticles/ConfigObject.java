package com.example.starter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;

public class ConfigObject
{
  public static void main(String[] args)
  {
    JsonObject data = new JsonObject();
    HashMap<Integer,Integer> hmap = new HashMap<>();
    data.put("Name","Vedant");
    data.put("Age",22);
    System.out.println("Json Object "+data);
    DeploymentOptions options = new DeploymentOptions().setConfig(data);
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new Verticle1(),options);
    vertx.deployVerticle(new Verticle2());
    System.out.println("Main Program "+Thread.currentThread().getName());

  }
}
class Verticle1 extends AbstractVerticle
{
  public void start()
  {
    System.out.println("Config Data "+config().getString("Name"));
    System.out.println("Vertciel 1 "+Thread.currentThread().getName());
  }
}

class Verticle2 extends AbstractVerticle
{
  public void start()
  {
    System.out.println("Verticle 2 "+Thread.currentThread().getName());
  }

}
