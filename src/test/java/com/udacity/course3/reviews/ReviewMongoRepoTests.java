package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;

import com.udacity.course3.reviews.model.ReviewDoc;
import com.udacity.course3.reviews.repo.ReviewMongoRepo;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
public class ReviewMongoRepoTests {

    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {
        ReviewDoc reviewDoc = new ReviewDoc();
        mongoTemplate.save(reviewDoc);
        assertThat(mongoTemplate.findAll(ReviewDoc.class, "reviews")).extracting("id")
                .containsOnly(reviewDoc.getId());
    }

    @Test
    public void testFindByReviewId(@Autowired ReviewMongoRepo reviewMongoRepo) {
        ReviewDoc reviewDoc = new ReviewDoc();
        reviewDoc.setReviewId(111111l);
        reviewMongoRepo.save(reviewDoc);
        Assert.assertNotNull(reviewMongoRepo.findByReviewId(reviewDoc.getReviewId()));
    }

    @Test
    public void testfindByReviewIdIn(@Autowired ReviewMongoRepo reviewMongoRepo) {
        ReviewDoc reviewDoc = new ReviewDoc();
        reviewDoc.setReviewId(111114l);
        reviewMongoRepo.save(reviewDoc);
        ReviewDoc reviewDoc2 = new ReviewDoc();
        reviewDoc2.setReviewId(111112l);
        reviewMongoRepo.save(reviewDoc2);
        Assert.assertEquals(2, reviewMongoRepo.findByReviewIdIn(new ArrayList<Long>() {
            { add(reviewDoc.getReviewId());
              add(reviewDoc2.getReviewId());
            }
        }).size());
    }

 }
