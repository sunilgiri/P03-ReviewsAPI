package com.udacity.course3.reviews.repo;

import com.udacity.course3.reviews.model.ReviewDoc;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewMongoRepo extends MongoRepository<ReviewDoc, String> {
    public Optional<ReviewDoc> findByReviewId(Long reviewId);
    public List<ReviewDoc> findByReviewIdIn(List<Long> reviewIds);
}
