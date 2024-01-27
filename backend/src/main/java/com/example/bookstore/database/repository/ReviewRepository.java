package com.example.bookstore.database.repository;

import com.example.bookstore.database.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByProductId(@RequestParam("productId") Long productId, Pageable pageable);

    Review findTopByUserEmailAndProductId(@RequestParam("userEmail")String userEmail, Long productId);
}
