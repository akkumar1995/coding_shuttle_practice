package com.avinash.kumar.inventory_service.service;

import com.avinash.kumar.inventory_service.dto.OrderRequestDto;
import com.avinash.kumar.inventory_service.dto.OrderRequestItemDto;
import com.avinash.kumar.inventory_service.dto.ProductDto;
import com.avinash.kumar.inventory_service.entity.Product;
import com.avinash.kumar.inventory_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory() {
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id) {
        log.info("Fetching Product with ID: {}", id);
        Optional<Product> inventory = productRepository.findById(id);
        return inventory.map(item -> modelMapper.map(item, ProductDto.class))
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }
    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto){
        Double totalPrice =0.0;
        for(OrderRequestItemDto item : orderRequestDto.getItems()){
            Long productId = item.getProductId();
            Integer qty = item.getQuantity();
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not Found"));
            if(product.getStock()<qty){
                throw new RuntimeException("Product cannot be fulfilled for quantity");
            }
            product.setStock(product.getStock()-qty);
            productRepository.save(product);
            totalPrice+= qty*product.getPrice();
        }
        return totalPrice;
    }
}
