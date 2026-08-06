package com.avinash.kumar.module3.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="product",
uniqueConstraints = {
        @UniqueConstraint(name="sku_unique",columnNames = {"sku"}),
        @UniqueConstraint(name="title_price_unique",columnNames = {"title","price"})
},
indexes = {
        @Index(name="sku_index",columnList = "sku ")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 20)
    private String sku;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
