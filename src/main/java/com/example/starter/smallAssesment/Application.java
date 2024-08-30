package com.example.starter.smallAssesment;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Application
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();
    String path="/home/vedant/Music/project_vertex/src/main/java/com/example/starter/smallAssesment/filenames.txt";
    tester(vertx);
  }
  public static Future<List<String>> readFile(Vertx vertx, String fileName)
  {
    List<String> files = new ArrayList<>();
    FileSystem file = vertx.fileSystem();
    file.readFile(fileName).onComplete(
      handler->{
        System.out.println(handler.result().toString().split("\n").length);
    });

    return Promise.<List<String>>promise().future();
  }

  public static void tester(Vertx vertx)
  {
    var p1 = Promise.promise();
    var p2 = Promise.promise();
    var p3 = Promise.promise();

    var f1 = p1.future();
    var f2 = p2.future();
    var f3 = p3.future();


    Future.all(f1,f2,f3).
      onSuccess(event -> System.out.println("ALL>>Every Future Successed"))
      .onFailure(event -> System.out.println("ALL>>One Of the Future Failed"));

    Future.any(f1,f2,f3).
      onComplete(event -> System.out.println("Result 0"))
      .onSuccess(event -> System.out.println("ANY>>One of the Future must Successed"))
      .onFailure(event -> System.out.println("ANY>>One Of the Future Failed"));

    Future.join(f1, f2, f3).
      onComplete(event -> System.out.println("Completed " + event.result()))
      .onSuccess(event -> System.out.println("Join>>One of the Future must Successed "))
      .onFailure(event -> System.out.println("Join>>One Of the Future Failed"));


    //Future.any(Arrays.asLibnst(f1,f2,f3)).onComplete();

    p3.complete("Failed");
    p2.complete("Failed");
    p3.complete("Failed");
  }
}
