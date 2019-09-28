package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Rating;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repo.ReviewRepo;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepoTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ReviewRepo reviewRepo;


    @Test
    public void componentsNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(reviewRepo).isNotNull();
    }

    @Test
    public void findByProductId() {

        Product product = getProduct();
        Review review = newReview();
        entityManager.persist(product);
        review.setProduct(product);
        assertThat(product.getId()).isNotNull();
        entityManager.persist(review);
        Comment comment = getComment();
        comment.setReview(review);
        entityManager.persist(comment);
        assertThat(reviewRepo.findByProductId(product.getId())).isNotNull();
        assertThat(reviewRepo.findByProductId(product.getId()).get(0)).isNotNull();
    }

    private Product getProduct() {

        Product product = new Product();
        product.setName("Shirt");
        product.setDescription("Plain shirt, full sleev");
        return product;

    }

    private Review newReview() {
        Review review = new Review();
        review.setRating(Rating.GOOD);
        review.setLogin("sunilgiri");
        review.setText("Fanstastic");
        review.setDate(new java.sql.Timestamp(new Date().getTime()));

        return review;
    }

    private Comment getComment() {

        Comment comment = new Comment();
        comment.setText("Just another comment");
        comment.setLoginId("sunilgiri");

        return comment;

    }


}
