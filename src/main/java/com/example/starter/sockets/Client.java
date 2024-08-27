package com.example.starter.sockets;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

public class Client extends AbstractVerticle {
  @Override
  public void start() {
    NetClient client = vertx.createNetClient();

    client.connect(1234, "localhost", res -> {
      if (res.succeeded()) {
        System.out.println("Connected to server!");

        NetSocket socket = res.result();
        socket.write("Hello, Vert.x!");

        socket.handler(buffer -> {
          System.out.println("Received reply: " + buffer.toString());
        });

        socket.closeHandler(v -> {
          System.out.println("Connection closed by server");
        });
      } else {
        System.out.println("Failed to connect to server");
      }
    });
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Client());
  }
}

