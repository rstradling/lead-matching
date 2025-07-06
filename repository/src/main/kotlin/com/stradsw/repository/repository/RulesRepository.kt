package com.stradsw.repository.repository

import com.stradsw.repository.entity.RuleEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RulesRepository : ReactiveCrudRepository<RuleEntity, Long> {
    // Spring Data will automatically implement the methods from ReactiveCrudRepository
}
