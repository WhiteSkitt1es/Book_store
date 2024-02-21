package com.example.bookstore.controller;


import com.example.bookstore.database.entity.Product;
import com.example.bookstore.service.ProductService;
import com.example.bookstore.utils.JWTParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private String extractEmail(String token) {
        String email = JWTParser.jwtExtraction(token, "\"sub\"");
        if (email != null) {
            return email;
        }
        return "";
    }

    @PutMapping("/secure/order")
    public Product orderProduct(@RequestHeader(value = "Authorization") String token,
                                @RequestParam Long productId) throws Exception {
        return productService.orderProduct(extractEmail(token), productId);
    }

    @GetMapping("/secure/isOrder/buyUser")
    public boolean orderProductByUser(@RequestHeader(value = "Authorization") String token,
                                      @RequestParam Long productId) {
        return productService.orderProductByUser(extractEmail(token), productId);
    }

    @GetMapping("/secure/currentOrder/count")
    public int currentOrderCount(@RequestHeader(value = "Authorization") String token) {
        return productService.currentOrderCount(extractEmail(token));
    }
}
