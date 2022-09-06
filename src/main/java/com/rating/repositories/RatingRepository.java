package com.rating.repositories;

import com.rating.enitities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findByCourseId(long courseId);
}