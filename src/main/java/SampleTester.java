import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class SampleTester {

  private static Vertx vertx = Vertx.vertx();

  static public void failedPromise(int ...value) {
    final Promise<Void> promise = Promise.promise();
    final Future<Void> future = promise.future();

    System.out.println("Current Thread "+Thread.currentThread().getName());
    System.out.println("Address in failedPromise "+vertx);

    vertx.setTimer(5000, event -> {
      promise.fail("Failed Promise");
      System.out.println("Code Executed even after failure ");
    });

    future.onFailure(event -> System.out.println("Promise Failed " + event.toString()));
  }

  public static void main(String[] args) {
    JsonObject object = new JsonObject();
    Promise<String> promise = Promise.promise();
    Future<String> future = promise.future();

    failedPromise(10, 20);

    System.out.println("Address in main method! "+vertx);

    vertx.setTimer(5000, event -> promise.complete("Completed"));

    future
      .onSuccess(handler -> {
        System.out.println("Future Succeed");
      })
      .onComplete(handler -> {
        System.out.println("Future Completed " + future.result());
      })
      .onFailure(handler -> {
        System.out.println("Error " + handler.getMessage());
      });
  }
}
