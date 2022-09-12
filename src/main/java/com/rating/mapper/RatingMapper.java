package com.rating.mapper;

import com.rating.enitities.Rating;
import com.rating.model.RatingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(source = "ratingValue", target = "ratingStars")
    Rating ratingDtoToRating(final RatingDto ratingDto);

    @Mapping(source = "ratingStars", target = "ratingValue")
    RatingDto ratingToRatingDto(final Rating rating);
}
