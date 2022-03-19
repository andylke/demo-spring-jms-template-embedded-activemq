package com.github.andylke.demo.message;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderScheduler.class);

  @Autowired private JmsTemplate jmsTemplate;

  @Scheduled(fixedDelay = 5000)
  public void sendMessage() {
    final Message message = new Message();
    message.setText("hello");
    message.setTimestamp(Instant.now());

    LOGGER.info("Sending {}", message);
    jmsTemplate.convertAndSend("message-queue", message);
  }
}
