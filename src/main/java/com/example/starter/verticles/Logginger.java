package com.example.starter.verticles;

import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logginger
{
  public static void main(String[] args)
  {
    Logger log = LoggerFactory.getLogger(Logginger.class);
    log.debug("Nice");
  }
}
