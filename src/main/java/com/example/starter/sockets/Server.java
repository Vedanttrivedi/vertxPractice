package com.example.starter.sockets;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;

public class Server extends AbstractVerticle {
  @Override
  public void start() {
    NetServer server = vertx.createNetServer();

    server.connectHandler(socket -> {
      System.out.println("New connection from " + socket.remoteAddress());

      socket.handler(buffer -> {
        System.out.println("Received data: " + buffer.toString());
        socket.write("Echo: " + buffer.toString());
      });

      socket.closeHandler(v -> {
        System.out.println("Connection closed");
      });
    });

    server.listen(1234, "localhost", res -> {
      if (res.succeeded()) {
        System.out.println("Server is now listening on port 1234");
      } else {
        System.out.println("Failed to bind!");
      }
    });
  }

  public static void main(String[] args)
  {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Server());
  }
}
