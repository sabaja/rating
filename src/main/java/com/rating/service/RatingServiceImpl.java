package com.rating.service;

import com.rating.enitities.Rating;
import com.rating.event.RatingEventMessage;
import com.rating.mapper.RatingMapper;
import com.rating.repositories.RatingRepository;

import java.util.Objects;

public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final RatingMapper mapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper mapper) {
        this.ratingRepository = ratingRepository;
        this.mapper = mapper;
    }

    /**
     * @param ratingEventMessage
     * @return
     */
    @Override
    public RatingEventMessage saveOrUpdateRating(RatingEventMessage ratingEventMessage) {
        if (Objects.nonNull(ratingEventMessage)) {
            final Long courseId = ratingEventMessage.getCourseId();
            if (Objects.nonNull(courseId) && courseId.compareTo(0L) > 0) {
                return ratingRepository.findById(courseId)
                        .map(entity -> this.updateRating(entity, ratingEventMessage))
                        .map(ratingRepository::save)
                        .map(mapper::ratingToRatingEvent)
                        .orElseGet(() -> saveRatingEvent(ratingEventMessage));
            }
        }
        return null;
    }

    private Rating updateRating(Rating rating, RatingEventMessage ratingEventMessage) {
        rating.setRatingStars(ratingEventMessage.getRatingValue());
        return rating;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public RatingEventMessage get(Long id) {
        return null;
    }

    private RatingEventMessage saveRatingEvent(RatingEventMessage ratingEventMessage) {
        final Rating rating = mapper.ratingEventToRating(ratingEventMessage);
        return mapper.ratingToRatingEvent(ratingRepository.save(rating));
    }
}
