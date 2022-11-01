package com.rating.configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    /*
      https://stackoverflow.com/questions/53706538/spring-amqp-rabbitmq-rpc-priority-queue
      https://www.javainuse.com/messaging/rabbitmq/exchange
    */
    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}