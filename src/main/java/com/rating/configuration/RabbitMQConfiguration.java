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

    @Value("${rating.exchange}")
    private String ratingExchange ;

    @Value("${rating.status.queue}")
    private String ratingStatusQueue;

    @Value("${rating.routing.key}")
    private String routingKey;

    @Bean
    Queue queue() {
        return new Queue(ratingStatusQueue);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(ratingExchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
    }

    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}