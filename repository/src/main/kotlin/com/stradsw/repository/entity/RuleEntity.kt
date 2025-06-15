package com.stradsw.repository.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("rules")
data class RuleEntity(
    @Id val id: Long?,
    val name: String?,
    val description: String?
)

