package com.example.starter.dataStore;

import io.vertx.core.json.JsonObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class JsonObjecter
{
  public static void main(String[] args)
  {
    //JsonObject uses internally linkedhashmap to store data.
    JsonObject object = new JsonObject();
    object.put("Name","Vedant");
    object.put("Age",22);

    //Encode the String to send over network
    String encodedObject  =object.encode();
    System.out.println("Encoded "+encodedObject);

    //Decode the string object
    final JsonObject newObject = new JsonObject(encodedObject);
    System.out.println("Json Object "+newObject);

    //JsonObject to map
    //it must me type of string,integer
    Map<String,Object> map = new HashMap<>();
    map.put("1",10);
    map.put("2",10);

    JsonObject mapToObject = new JsonObject(map);
    System.out.println("Map to object "+mapToObject);

    //getting data from hashmap
    System.out.println("Data Name "+object.getString("Name")+"\t Age "+object.getInteger("Age"));

    //Custom Object To JsonObject
    //json core -> databind and annotations versions must be same in pom.xml to
    //avoid jackson errors
    Person p1 = new Person("Vedant",23);
    final JsonObject p1Json = JsonObject.mapFrom(p1);
    System.out.println(p1Json);


    //Json object to class
    //when deserializing we must provide default constructor in Class
    Person p2 = p1Json.mapTo(Person.class);
    System.out.println("Custom object "+p2);
  }
}



class Person
{
  String name;
  int age;

  public Person()
  {

  }
  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
      "name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}
