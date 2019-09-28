package com.udacity.course3.reviews.repo;

import com.udacity.course3.reviews.model.Comment;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment,Long> {
    public List<Comment> findByReviewId(Long reviewId);

}
