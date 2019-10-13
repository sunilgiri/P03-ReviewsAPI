package com.udacity.course3.reviews.repo;

import com.udacity.course3.reviews.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReviewRepo extends CrudRepository<Review, Long> {
    public List<Review> findByProductId(Long productId);

    @Query("select id from Review r where r.product.id = :productId")
    List<Long> findByproductId(@Param("productId") Long productId);
}
