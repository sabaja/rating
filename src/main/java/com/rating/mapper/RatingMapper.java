package com.rating.mapper;

import com.rating.enitities.Rating;
import com.rating.event.RatingEventMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    Rating ratingEventToRating(final RatingEventMessage ratingEventMessage);

    RatingEventMessage ratingToRatingEvent(final Rating rating);
}
