package com.udacity.course3.reviews.repo;

import com.udacity.course3.reviews.model.Review;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Review, Long> {
    public List<Review> findByProductId(Long productId);
}
