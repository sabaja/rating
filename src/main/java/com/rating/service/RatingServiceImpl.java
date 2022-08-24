package com.rating.service;

import com.rating.enitities.Rating;
import com.rating.event.RatingEvent;
import com.rating.mapper.RatingMapper;
import com.rating.repositories.RatingRepository;

import java.util.Objects;
import java.util.Optional;

public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private RatingMapper mapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper mapper) {
        this.ratingRepository = ratingRepository;
        this.mapper = mapper;
    }

    /**
     * @param ratingEvent
     * @return
     */
    @Override
    public RatingEvent save(RatingEvent ratingEvent) {
        if (Objects.nonNull(ratingEvent)) {
            final Long courseId = ratingEvent.getCourseId();
            if (Objects.nonNull(courseId) && courseId.compareTo(0L) > 0)) {
                return Optional.ofNullable(ratingRepository.findById(courseId))
                        .map((entity) -> this.updateRating(entity, ratingEvent)
                        .map(     ratingRepository::save)
                             .map(mapper::ratingToRatingEvent)
                        .orElseGet( () -> {
                             return saveRatingEvent(ratingEvent);         
                        });
            }
        }
        return null;
    }

    private RatingEvent updateRating(Rating rating, RatingEvent ratingEvent) {
        rating.setRatingValue(ratingEvent.getRatingStars());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public RatingEvent get(Long id) {
        return null;
    }
                             
    private RatingEvent  saveRatingEvent(RatingEvent ratingEvent) {
        final Rating rating = mapper.ratingEventToRating(ratingEvent);
        return mapper.ratingToRatingEvent(ratingRepository.save(rating));
    }                             
}
