package com.rating.controller;

import com.rating.model.RatingDto;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;


    @PutMapping("/rating/update/{id}")
    @ResponseBody
    public Mono<RatingDto> putRatingCourse(@PathVariable Long id, @RequestBody RatingDto dto) {
        RatingDto ratingDto = ratingService.saveOrUpdateRating(id, dto);
        return Mono.justOrEmpty(ratingDto);
    }

    @GetMapping("/rating/{id}")
    @ResponseBody
    public Mono<RatingDto> getRatingCourse(@PathVariable Long id) {
        final RatingDto ratingDto = ratingService.get(id);
        return Mono.justOrEmpty(ratingDto);
    }

    @GetMapping("/rating/all")
    @ResponseBody
    public Flux<RatingDto> getAllRatingCourse() {
        final List<RatingDto> ratingDto = ratingService.getAll();
        return Flux.fromIterable(ratingDto);
    }
}
