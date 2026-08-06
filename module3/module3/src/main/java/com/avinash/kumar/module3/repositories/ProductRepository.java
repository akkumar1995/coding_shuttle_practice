package com.avinash.kumar.module3.repositories;

import com.avinash.kumar.module3.entities.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
     List<Product> findByTitle(String title);

     List<Product> findByTitleLike(String titleLike);

     @Query("select e from Product e where e.title=?1 and e.price=?2")
     Optional<Product> findByTitleAndPrice(String title, BigDecimal price);

     List<Product> findByTitleOrderByPrice(String title);

     List<Product> findBy(Sort sort);
}
