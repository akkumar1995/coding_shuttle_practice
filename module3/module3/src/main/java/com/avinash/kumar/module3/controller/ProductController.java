package com.avinash.kumar.module3.controller;

import com.avinash.kumar.module3.entities.Product;
import com.avinash.kumar.module3.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping()
    public List<Product> getAllProducts(@RequestParam(defaultValue = "id") String sortBy){
        return
//                productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy,"price"));
        productRepository.findBy(Sort.by(Sort.Order.desc(sortBy)));
    }

    @GetMapping("/page")
    public List<Product> getAllProductsPage(){
        Pageable pageable = PageRequest.of(1,5,Sort.by(Sort.Direction.DESC,"title"));
        return productRepository.findAll(pageable).getContent();
    }
}
