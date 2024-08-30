package com.example.starter.futureAndPromises;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

/*
* compose
 can be used for chaining futures:

when the current future succeeds, apply the given function, that returns a future.
* When this returned future completes, the composition succeeds.

when the current future fails, the composition fails
* */
public class FutureCompose
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

//    FileSystem fs = vertx.fileSystem();
//
//    Future<Void> future = fs
//      .createFile("sampleVertx.txt")
//      .compose(v -> {
//        // When the file is created (fut1), execute this:
//        System.out.println("File Created "+v);
//        return fs.writeFile("sampleVertx.txt", Buffer.buffer("lll"));
//      })
//      .compose(v -> {
//        // When the file is written (fut2), execute this:
//        System.out.println("Data is written now moving "+v);
//        return fs.move("sampleVertx.txt", "/home/vedant");
//      });
//    future.onComplete(event -> System.out.println(event.result()));
//

    vertx.deployVerticle(WriteFileVerticle.class.getName(),new DeploymentOptions().setInstances(1));

  }

  static public class WriteFileVerticle extends AbstractVerticle
  {
    @Override
    public void start() throws Exception
    {
      System.out.println("Verticle Loaded");
      try{

        System.out.println("Thread Info "+Thread.currentThread().getName());
        Thread.sleep(3000);
        //after that create file once created write in the file
        FileSystem fs = vertx.fileSystem();
        fs.createFile("vertxer.txt").
          compose(file-> fs.writeFile("vertxer.txt",Buffer.buffer("new Data")))
          .onComplete(event -> System.out.println("I have created and written into the file"));

        vertx.eventBus().<Pojo>request("12345","new Pojo(Vedant,1234)");
        vertx.eventBus().<Pojo>consumer("12345",event -> {
          System.out.println("Consumed "+event.body());
        });
      }catch (InterruptedException e){
        System.out.println(e.getMessage());
      }
    }
  }
}

class Pojo{
  private String name;
  private int id;

  public Pojo(String name, int id)
  {
    this.name = name;
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
