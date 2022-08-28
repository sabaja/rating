package com.rating.event.handler;

import com.rating.event.RatingEventMessage;
import com.rating.model.RatingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RatingEventConsumer {

    @RabbitListener(queues = "rating_request_queue", concurrency = "10")
    public RatingEventMessage receive(RatingDto ratingDto) {
        log.info("server receive a request of Rating information: {}", ratingDto);
        RatingEventMessage response = new RatingEventMessage();
        response.setCourseId(ratingDto.getCourseId());
        response.setRatingValue(5.00D);
        return response;
    }
}