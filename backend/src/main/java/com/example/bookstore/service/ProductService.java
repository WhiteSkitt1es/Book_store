package com.example.bookstore.service;

import com.example.bookstore.database.entity.Order;
import com.example.bookstore.database.entity.Product;
import com.example.bookstore.database.repository.OrderRepository;
import com.example.bookstore.database.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Product orderProduct(String userEmail, Long productId) throws Exception {

        Optional<Product> product = productRepository.findById(productId);

        Order order = orderRepository.findByUserEmailAndProductId(userEmail, productId);

        if (product.isEmpty() || order != null || product.get().getCopiesAvailable() <= 0) {
            throw new Exception("Ошибка!");
        }

        product.get().setCopiesAvailable(product.get().getCopiesAvailable() - 1);
        productRepository.save(product.get());

        Order buildOrder = Order.builder()
                .userEmail(userEmail)
                .productId(product.get().getId())
                .orderDate(LocalDate.now().toString())
                .returnDate(LocalDate.now().plusDays(7).toString())
                .build();

        orderRepository.save(buildOrder);

        return product.get();
    }

    public boolean orderProductByUser(String userEmail, Long productId) {
        return orderRepository.findByUserEmailAndProductId(userEmail, productId) != null;
    }

    public int currentOrderCount(String userEmail) {
        return orderRepository.findProductByUserEmail(userEmail).size();
    }
}
