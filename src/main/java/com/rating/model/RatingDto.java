package com.rating.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class RatingDto implements Serializable {
    private Double ratingValue;
    private Long courseId;
}

