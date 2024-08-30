package com.example.starter.cutsomObjectSender;

import io.vertx.core.json.JsonObject;

public class Pojo {
  private String name;
  private String id;

  public Pojo(String name, String id)
  {
    this.name = name;
    this.id = id;
  }
  public Pojo()
  {}

  public JsonObject toJson()
  {
    return new JsonObject()
      .put("name",name)
      .put("id",id);
  }

  public Pojo fromJson(JsonObject jsonObject)
  {

    return new Pojo(jsonObject.getString("name"),jsonObject.getString("id"));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Pojo{" +
      "name='" + name + '\'' +
      ", id='" + id + '\'' +
      '}';
  }
}
