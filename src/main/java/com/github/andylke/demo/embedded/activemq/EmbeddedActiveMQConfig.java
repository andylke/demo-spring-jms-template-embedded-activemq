package com.github.andylke.demo.embedded.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableConfigurationProperties({ActiveMQProperties.class})
public class EmbeddedActiveMQConfig {

  @Autowired private ActiveMQProperties activeMQProperties;

  @Bean(initMethod = "start", destroyMethod = "stop")
  public BrokerService broker() throws Exception {
    final BrokerService brokerService = new BrokerService();
    brokerService.addConnector(activeMQProperties.getBrokerUrl());
    brokerService.setPersistent(false);
    return brokerService;
  }

  @Bean
  public MappingJackson2MessageConverter mappingJackson2MessageConverter(
      ObjectMapper objectMapper) {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    converter.setObjectMapper(objectMapper);
    return converter;
  }

  @Bean
  public ActiveMQConnectionFactory jmsConnectionFactory() {
    final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(activeMQProperties.getBrokerUrl());
    connectionFactory.setTrustAllPackages(true);
    return connectionFactory;
  }
}
