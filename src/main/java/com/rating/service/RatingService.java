package com.rating.service;


import com.rating.model.RatingDto;

import java.util.List;

public interface RatingService {

    RatingDto saveOrUpdateRating(Long id, RatingDto ratingDto);

    RatingDto get(Long id);

    List<RatingDto> getAll();
}
