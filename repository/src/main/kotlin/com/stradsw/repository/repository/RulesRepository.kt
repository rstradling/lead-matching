package com.stradsw.repository.repository

import com.stradsw.repository.entity.RuleEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface RulesRepository : ReactiveCrudRepository<RuleEntity, Long> {

    fun save(rule: RuleEntity): Mono<RuleEntity> {
        return save(RuleEntity(rule.id, rule.name, rule.description))
            .map { RuleEntity(it.id, it.name, it.description) }
    }

    override fun findById(id: Long): Mono<RuleEntity> {
        return findById(id)
            .map { RuleEntity(it.id, it.name, it.description) }
    }

    override fun findAll(): Flux<RuleEntity> {
        return findAll()
            .map { RuleEntity(it.id, it.name, it.description) }
    }

    override fun deleteById(id: Long): Mono<Void> {
        return deleteById(id)
    }
}
