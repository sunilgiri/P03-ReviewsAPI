package com.udacity.course3.reviews.repo;

import com.udacity.course3.reviews.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepo extends CrudRepository<Comment, Long> {

}
