package com.udacity.course3.reviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class ReviewDoc implements Serializable {

    @Id
    @JsonIgnore
    private String id;

    private Long reviewId;

    private Rating rating;

    private String login;

    private String text;

    private List<CommentDoc> comments;


    public ReviewDoc() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getLogin() {
        return login;
    }

    public List<CommentDoc> getComments() {
        return comments;
    }

    public void setComments(List<CommentDoc> comments) {
        this.comments = comments;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
