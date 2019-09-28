package com.udacity.course3.reviews.repo;

import com.udacity.course3.reviews.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product,Long> {

}
