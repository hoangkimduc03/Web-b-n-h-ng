package com.demo.repo;

import com.demo.model.Category;
import com.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id=?1 AND p.name LIKE ?2 and p.price BETWEEN ?3 AND ?4")
    Page<Product> searchByCategoryNamePrice(String cid, String kw, int minPrice, int maxPrice, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1 and p.price BETWEEN ?2 AND ?3")
    Page<Product> searchByNamePrice(String kw, int minPrice, int maxPrice, Pageable pageable);


    @Query("SELECT o FROM Product o WHERE o.name like ?1")
    Page<Product> findByKeywords(String query, Pageable pageable);

    Page<Product> findByCategory(Category category, Pageable pageable);
}
