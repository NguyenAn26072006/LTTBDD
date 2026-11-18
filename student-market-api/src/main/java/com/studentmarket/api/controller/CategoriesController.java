package com.studentmarket.api.controller;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

private final JdbcTemplate jdbcTemplate;

public CategoriesController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}

// Lấy tất cả danh mục
@GetMapping
public List<Map<String, Object>> getAllCategories() {

    String sql = "SELECT * FROM categories";

    return jdbcTemplate.queryForList(sql);
}
}