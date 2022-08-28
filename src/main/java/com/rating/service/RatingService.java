package com.rating.service;

import com.rating.event.RatingEventMessage;

public interface RatingService {

    RatingEventMessage saveOrUpdateRating(RatingEventMessage ratingEventMessage);

    RatingEventMessage get(Long id);
}
