package com.rating.event.handler;

import com.rating.enitities.Rating;
import com.rating.event.RatingEventMessage;
import com.rating.model.RatingDto;
import com.rating.repositories.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class RatingEventConsumer {

    @Autowired
    private RatingRepository ratingRepository;

    @RabbitListener(queues = "rating_status_request_queue", concurrency = "10")
    public RatingEventMessage receiveStatusRatingInformation(RatingDto ratingDto) {
        log.info("Server received a request of Rating information: {}", ratingDto);
        final Long courseId = ratingDto.getCourseId();
        if (Objects.nonNull(courseId)) {
            final Rating rating = ratingRepository.findByCourseId(courseId);
            if (Objects.nonNull(rating)) {
                return createRatingEventMessage(rating);
            }
            log.error("No Rating found with id {}", courseId);
        }
        log.error("No Rating found ...");
        return createDefaultRatingEventMessage();
    }


    @RabbitListener(queues = "rating_update_request_queue", concurrency = "10")
    public RatingEventMessage receiveUptadeRatingInformation(RatingDto ratingDto) {
        log.info("Server received a request of updating Rating information: {}", ratingDto);
        final Long courseId = ratingDto.getCourseId();
        if (Objects.nonNull(courseId)) {
            Rating rating = ratingRepository.findByCourseId(courseId);
            if (Objects.nonNull(rating)) {
                rating.setRatingStars(ratingDto.getRatingValue());
                return createRatingEventMessage(ratingRepository.save(rating));
            }
            log.error("No Rating found with id {}", courseId);
        }
        log.error("No Rating found ...");
        return createDefaultRatingEventMessage();
    }

    private RatingEventMessage createRatingEventMessage(Rating rating) {
        RatingEventMessage message = new RatingEventMessage();
        message.setRatingValue(rating.getRatingStars());
        message.setCourseId(rating.getCourseId());
        return message;
    }

    private RatingEventMessage createDefaultRatingEventMessage() {
        RatingEventMessage message = new RatingEventMessage();
        message.setRatingValue(0.0D);
        message.setCourseId(0L);
        return message;
    }
}