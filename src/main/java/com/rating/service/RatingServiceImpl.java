package com.rating.service;

import com.rating.enitities.Rating;
import com.rating.mapper.RatingMapper;
import com.rating.model.RatingDto;
import com.rating.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final RatingMapper mapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper mapper) {
        this.ratingRepository = ratingRepository;
        this.mapper = mapper;
    }

    /**
     * @param ratingDto
     * @return
     */
    @Override
    public RatingDto saveOrUpdateRating(RatingDto ratingDto) {
        if (Objects.nonNull(ratingDto)) {
            final Long courseId = ratingDto.getCourseId();
            if (Objects.nonNull(courseId) && courseId.compareTo(0L) > 0) {
                return ratingRepository.findById(courseId)
                        .map(entity -> this.updateRating(entity, ratingDto))
                        .map(ratingRepository::save)
                        .map(mapper::ratingToRatingDto)
                        .orElseGet(() -> saveRating(ratingDto));
            }
        }
        return null;
    }

    @Override
    public RatingDto get(Long id) {
        return null;
    }

    private Rating updateRating(Rating rating, RatingDto ratingDto) {
        rating.setRatingStars(ratingDto.getRatingValue());
        return rating;
    }

    private RatingDto saveRating(RatingDto ratingDto) {
        final Rating rating = mapper.ratingDtoToRating(ratingDto);
        return mapper.ratingToRatingDto(ratingRepository.save(rating));
    }
}
