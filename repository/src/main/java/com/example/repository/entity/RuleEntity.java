package com.example.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("rules")
public record RuleEntity(
        @Id Long id,
        String name,
        String description
) {}