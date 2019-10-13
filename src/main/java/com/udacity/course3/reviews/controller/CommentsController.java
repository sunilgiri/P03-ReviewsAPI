package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.CommentDoc;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.ReviewDoc;
import com.udacity.course3.reviews.repo.CommentRepo;
import com.udacity.course3.reviews.repo.ReviewMongoRepo;
import com.udacity.course3.reviews.repo.ReviewRepo;
import com.udacity.course3.reviews.service.ReviewNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ReviewMongoRepo reviewMongoRepo;

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

        Optional<Review> review = reviewRepo.findById(reviewId);
        Optional<ReviewDoc> optReviewDoc = reviewMongoRepo.findByReviewId(reviewId);

        if (review.isPresent() && optReviewDoc.isPresent()) {

            System.out.println("SAVE COMMENTS");
            comment.setReview(review.get());
            commentRepo.save(comment);
            CommentDoc commentDoc = copyToDoc(comment);
            ReviewDoc reviewDoc = optReviewDoc.get();
            if(reviewDoc.getComments()!=null) {
                reviewDoc.getComments().add(commentDoc);
            } else {
                List<CommentDoc> commentDocs = new ArrayList<>();
                commentDocs.add(commentDoc);
                reviewDoc.setComments(commentDocs);
             }

            return ResponseEntity.ok(reviewMongoRepo.save(reviewDoc));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    //Create comment doc from comment
    private CommentDoc copyToDoc(Comment comment) {
        CommentDoc commentDoc = new CommentDoc();
        commentDoc.setLogin(comment.getLogin());
        commentDoc.setText(comment.getText());

        return commentDoc;
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

        Optional<Review> review = reviewRepo.findById(reviewId);
        if (review.isPresent()) {

            return commentRepo.findByReviewId(review.get().getId());
        } else {

            throw new ReviewNotFoundException();

        }

    }
}