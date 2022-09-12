package com.rating.controller;

import com.rating.model.RatingDto;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @PutMapping("/rating/update/{id}")
    @ResponseBody
    public Mono<RatingDto> putRatingCourse(@RequestBody RatingDto dto) {
        RatingDto ratingDto = ratingService.saveOrUpdateRating(dto);
        return Mono.justOrEmpty(ratingDto);
    }
}
