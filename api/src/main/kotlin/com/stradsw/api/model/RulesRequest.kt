package com.stradsw.api.model;

import jakarta.validation.constraints.NotBlank

data class RulesRequest(
    @NotBlank val name: String,
    @NotBlank val description: String
)
