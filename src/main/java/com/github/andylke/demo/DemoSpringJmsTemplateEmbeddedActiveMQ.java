package com.github.andylke.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class DemoSpringJmsTemplateEmbeddedActiveMQ {

  public static void main(String[] args) {
    SpringApplication.run(DemoSpringJmsTemplateEmbeddedActiveMQ.class, args);
  }
}
