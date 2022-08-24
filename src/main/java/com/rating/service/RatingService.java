package com.rating.service;

import com.rating.event.RatingEvent;

public interface RatingService {

    RatingEvent saveOrUpdateRating(RatingEvent ratingEvent);

    RatingEvent get(Long id);
}
