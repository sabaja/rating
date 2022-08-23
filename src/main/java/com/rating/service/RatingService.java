package com.rating.service;

import com.rating.event.RatingEvent;

public interface RatingService {

    RatingEvent save(RatingEvent ratingEvent);

    RatingEvent get(Long id);
}
