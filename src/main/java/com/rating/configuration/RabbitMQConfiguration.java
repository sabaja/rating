package com.rating.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    /* https://stackoverflow.com/questions/53706538/spring-amqp-rabbitmq-rpc-priority-queue */

    @Value("${rating.status.exchange}")
    private String ratingExchange ;

    @Value("${rating.status.queue}")
    private String ratingStatusQueue;

    @Value("${rating.status.routing.key}")
    private String routingKey;

    @Bean
    Queue queueStatus() {
        return new Queue(ratingStatusQueue);
    }

    @Bean(name = "ratingStatusExchange")
    DirectExchange exchangeRatingStatus() {
        return new DirectExchange(ratingExchange);
    }

    @Bean
    Binding bindingRatingStatus() {
        return BindingBuilder.bind(queueStatus()).to(exchangeRatingStatus()).with(routingKey);
    }

    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}