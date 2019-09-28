package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repo.ProductRepo;
import com.udacity.course3.reviews.repo.ReviewRepo;
import com.udacity.course3.reviews.service.ProductNotFoundException;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ReviewRepo reviewRepo;

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
    public ResponseEntity<?> createReviewForProduct(@Valid @RequestBody Review review,
            @PathVariable("productId") Long productId) {
        //throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);

        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            review.setProduct(product.get());
            return ResponseEntity.ok(reviewRepo.save(review));
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
        // throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);

        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            return ResponseEntity.ok(reviewRepo.findByProductId(product.get().getId()));
        } else {
            throw new ProductNotFoundException();
        }

    }
}