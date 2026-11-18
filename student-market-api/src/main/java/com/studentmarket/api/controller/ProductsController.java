package com.studentmarket.api.controller;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final JdbcTemplate jdbcTemplate;

    public ProductsController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Lấy danh sách tất cả sản phẩm
    @GetMapping
    public List<Map<String, Object>> getAllProducts() {

        String sql = "SELECT * FROM products ORDER BY id DESC";

        return jdbcTemplate.queryForList(sql);
    }
    @GetMapping("/{id}")
    public Map<String, Object> getProductById(
            @org.springframework.web.bind.annotation.PathVariable int id) {

        String sql = "SELECT * FROM products WHERE id = ?";

        List<Map<String, Object>> result =
                jdbcTemplate.queryForList(sql, id);

        if (result.isEmpty()) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Không tìm thấy sản phẩm"
            );
        }

        return result.get(0);
    }
    @GetMapping("/category/{categoryId}")
    public List<Map<String, Object>> getProductsByCategory(
            @org.springframework.web.bind.annotation.PathVariable int categoryId) {

        String sql = "SELECT * FROM products " +
                "WHERE category_id = ? " +
                "AND status = 'AVAILABLE' " +
                "ORDER BY id DESC";

        return jdbcTemplate.queryForList(sql, categoryId);
    }
    @GetMapping("/search")
    public List<Map<String, Object>> searchProducts(
            @org.springframework.web.bind.annotation.RequestParam String keyword) {

        String sql = "SELECT * FROM products " +
                "WHERE status = 'AVAILABLE' " +
                "AND (name LIKE ? OR description LIKE ?) " +
                "ORDER BY id DESC";

        String searchKeyword = "%" + keyword + "%";

        return jdbcTemplate.queryForList(
                sql, searchKeyword, searchKeyword);
    }
}
