package com.rating.service;


import com.rating.model.RatingDto;

public interface RatingService {

    RatingDto saveOrUpdateRating(RatingDto ratingDto);

    RatingDto get(Long id);
}
