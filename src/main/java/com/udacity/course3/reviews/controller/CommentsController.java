package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repo.CommentRepo;
import com.udacity.course3.reviews.repo.ReviewRepo;
import com.udacity.course3.reviews.service.ReviewNotFoundException;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ReviewRepo reviewRepo;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation. 2. Check for
     * existence of review. 3. If review not found, return NOT_FOUND. 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable Long reviewId,
            @Valid @RequestBody Comment comment) {
        // throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);

        Optional<Review> review = reviewRepo.findById(reviewId);

        if (review.isPresent()) {
            comment.setReview(review.get());
            return ResponseEntity.ok(commentRepo.save(comment));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review. 3. If review not found, return NOT_FOUND. 4. If found,
     * return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public @ResponseBody
    List<?> listCommentsForReview(@PathVariable("reviewId") Long reviewId) {
        //throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);

        Optional<Review> review = reviewRepo.findById(reviewId);
        if (review.isPresent()) {

            return commentRepo.findByReviewId(review.get().getId());
        } else {

            throw new ReviewNotFoundException();

        }

    }
}