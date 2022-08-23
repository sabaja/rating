package com.rating.mapper;

import com.rating.enitities.Rating;
import com.rating.event.RatingEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    Rating ratingEventToRating(final RatingEvent ratingEvent);

    RatingEvent ratingToRatingEvent(final Rating rating);
}
