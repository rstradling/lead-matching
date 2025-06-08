package com.example.api.model;

import jakarta.validation.constraints.NotBlank;

public record RulesRequest(
        @NotBlank String name,
        @NotBlank String description
) {}