package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Rating;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.ReviewDoc;
import com.udacity.course3.reviews.repo.ProductRepo;
import com.udacity.course3.reviews.repo.ReviewMongoRepo;
import com.udacity.course3.reviews.repo.ReviewRepo;
import com.udacity.course3.reviews.service.ProductNotFoundException;
import com.udacity.course3.reviews.service.ReviewService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewMongoRepo reviewMongoRepo;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation. 2. Check for existence
     * of product. 3. If product not found, return NOT_FOUND. 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@Valid @RequestBody ReviewDoc reviewDoc,
            @PathVariable("productId") Long productId) {

        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {

            Review review = new Review();
            review.setProduct(product.get());
            review.setRating(reviewDoc.getRating());
            review.setDate(new Timestamp(new Date().getTime()));
            review.setText(reviewDoc.getText());
            review.setLogin(reviewDoc.getLogin());
            reviewRepo.save(review);
            reviewDoc.setReviewId(review.getId());

            return ResponseEntity.ok(reviewMongoRepo.save(reviewDoc));
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(
            @PathVariable("productId") Long productId) {

        return ResponseEntity.ok(reviewService.findReviewsByProductId(productId));
    }
}