package com.example.starter.dataStore;

import io.netty.util.concurrent.SingleThreadEventExecutor;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonArrayer
{
  public static void main(String[] args)
  {
    //By Default Jsonarray uses arraylist to store data
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(12);
    jsonArray.add("Nice");
    jsonArray.add(new JsonObject().put("Name","Vedant").put("Age",22));
    System.out.println(jsonArray);
    List<Integer> data = Arrays.asList(1,2,34);
    jsonArray.add(data);
    System.out.println(jsonArray.getInteger(0));
  }
}
