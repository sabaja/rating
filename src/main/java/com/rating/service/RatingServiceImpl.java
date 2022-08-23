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
            if (Objects.nonNull(courseId)) {
                Optional.ofNullable(ratingRepository.findById(courseId))
                        .map(this::updateRating)
                        .orElseGet();
            }
        }
        return null;
    }

    private RatingEvent updateRating(Optional<Rating> rating) {
        return rating.map(value -> {
            final RatingEvent ratingEvent = mapper.ratingToRatingEvent(value);
            ratingRepository.u
        }).orElse(null);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public RatingEvent get(Long id) {
        return null;
    }
}
