package com.rating.event;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Event received when a rating has been added to the course.
 */
@RequiredArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class RatingEventMessage implements Serializable {

    private Double ratingValue;
    private Long courseId;
}
