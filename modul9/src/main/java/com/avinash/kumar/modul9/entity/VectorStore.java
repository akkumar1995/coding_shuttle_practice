package com.avinash.kumar.modul9.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "vector_store", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VectorStore {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(columnDefinition = "text")
    private String content;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String metadata;

    // Treat vector as raw column string/type if you do not have pgvector library types registered
    @Column(columnDefinition = "vector(1024)")
    private String embedding;
}
