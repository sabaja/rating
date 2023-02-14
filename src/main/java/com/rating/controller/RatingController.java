package com.rating.controller;

import com.rating.model.RatingDto;
import com.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {


    private final RatingService ratingService;


    @PutMapping("/{id}")
    @ResponseBody
    public Mono<RatingDto> putRatingCourse(@PathVariable Long id, @RequestBody RatingDto dto) {
        RatingDto ratingDto = ratingService.saveOrUpdateRating(id, dto);
        return Mono.justOrEmpty(ratingDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<RatingDto> getRatingCourse(@PathVariable Long id) {
        final RatingDto ratingDto = ratingService.get(id);
        return Mono.justOrEmpty(ratingDto);
    }

    @GetMapping("/")
    @ResponseBody
    public Flux<RatingDto> getAllRatingCourse() {
        final List<RatingDto> ratingDto = ratingService.getAll();
        return Flux.fromIterable(ratingDto);
    }
}
