package com.github.andylke.demo.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

  @JmsListener(destination = "message-queue")
  public void received(Message message) {
    LOGGER.info("{} received", message);
  }
}
