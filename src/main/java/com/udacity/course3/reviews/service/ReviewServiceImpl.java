package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.ReviewDoc;
import com.udacity.course3.reviews.repo.ProductRepo;
import com.udacity.course3.reviews.repo.ReviewMongoRepo;
import com.udacity.course3.reviews.repo.ReviewRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    ReviewMongoRepo reviewMongoRepo;

    /**
     * Returns review documents for the given product Id.
     */
    public List<ReviewDoc> findReviewsByProductId(Long productId) {

        Optional<Product> product = productRepo.findById(productId);
        if (product.isPresent()) {
            return (List<ReviewDoc>) reviewMongoRepo
                    .findByReviewIdIn(reviewRepo.findByproductId(product.get().getId()));
        } else {
            throw new ProductNotFoundException();
        }

    }
}
