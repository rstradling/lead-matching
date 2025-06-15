package com.stradsw.service.service

import com.stradsw.repository.entity.RuleEntity
import com.stradsw.service.model.Rule
import com.stradsw.repository.repository.RulesRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RulesService(private val rulesRepository: RulesRepository) {

    fun createRule(rule: Rule): Mono<Rule> {
        val repoRule = RuleEntity(rule.id, rule.name, rule.description)
        return rulesRepository.save(repoRule).map { Rule(it.id, it.name, it.description) }
    }

    fun getRule(id: Long): Mono<Rule> {
        return rulesRepository.findById(id).map { Rule(it.id, it.name, it.description) }
    }

    fun getAllRules(): Flux<Rule> {
        return rulesRepository.findAll().map { Rule(it.id, it.name, it.description) }
    }

    fun updateRule(rule: Rule): Mono<Rule> {
        val repoRule = RuleEntity(rule.id, rule.name, rule.description)
        return rulesRepository.save(repoRule).map { Rule(it.id, it.name, it.description) }
    }

    fun deleteRule(id: Long): Mono<Void> {
        return rulesRepository.deleteById(id)
    }
}
