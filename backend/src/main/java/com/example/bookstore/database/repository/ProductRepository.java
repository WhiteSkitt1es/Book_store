package com.example.bookstore.database.repository;

import com.example.bookstore.database.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);

    Page<Product> findByCategory(@RequestParam("category") String category, Pageable pageable);
}
