package com.example.starter.futureAndPromises;

import io.vertx.core.Vertx;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class FuturePromiseExample {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    // Simulate an asynchronous operation using a Future
    Future<String> future = asyncOperation(vertx);

    // Handling the result or error from the Future
    future.onComplete(result -> {
      if (result.succeeded()) {
        System.out.println("Operation completed successfully: " + result.result());
      } else {
        System.out.println("Operation failed: " + result.cause().getMessage());
      }
    });
  }

  private static Future<String> asyncOperation(Vertx vertx) {
    Promise<String> promise = Promise.promise();

    // Simulate some asynchronous work using a Vert.x timer
    vertx.setTimer(1000, id -> {
      // You can succeed or fail the promise here
      if (7 > 0.5) {
        System.out.println("Thread "+Thread.currentThread().getName());
        promise.complete("Success!");
      } else {
        promise.fail(new RuntimeException("Something went wrong"));
      }
    });

    // Return the Future associated with the Promise
    return promise.future();
  }
}

