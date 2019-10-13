package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.ReviewDoc;
import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDoc> findReviewsByProductId(Long productId);
}
