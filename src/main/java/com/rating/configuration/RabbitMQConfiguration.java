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
    private String ratingStatusExchange;

    @Value("${rating.update.exchange}")
    private String ratingUpdateExchange ;
    @Value("${rating.status.queue}")
    private String ratingStatusQueue;

    @Value("${rating.update.queue}")
    private String ratingUpdateQueue;

    @Value("${rating.status.routing.key}")
    private String routingStatusKey;

    @Value("${rating.update.routing.key}")
    private String routingUpdateKey;

    @Bean
    Queue queueStatus() {
        return new Queue(ratingStatusQueue);
    }
    @Bean
    Queue queueUpdate() {
        return new Queue(ratingUpdateQueue);
    }

    @Bean(name = "ratingStatusExchange")
    DirectExchange exchangeRatingStatus() {
        return new DirectExchange(ratingStatusExchange);
    }

    @Bean(name = "ratingUpdateExchange")
    DirectExchange exchangeRatingUpdate() {
        return new DirectExchange(ratingUpdateExchange);
    }

    @Bean
    Binding bindingRatingStatus() {
        return BindingBuilder.bind(queueStatus()).to(exchangeRatingStatus()).with(routingStatusKey);
    }

    @Bean
    Binding bindingRatingUpdate() {
        return BindingBuilder.bind(queueStatus()).to(exchangeRatingUpdate()).with(routingUpdateKey);
    }

    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
//
//    @Bean(name = "ratingStatusListener")
//    public SimpleMessageListenerContainer ratingStatusListener(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        listenerContainer.setQueues(queueStatus());
//        return listenerContainer;
//    }
//    @Bean(name = "ratingStatusListener")
//    public SimpleMessageListenerContainer ratingUpdateListener(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        listenerContainer.setQueues(queueUpdate());
//        return listenerContainer;
//    }

}