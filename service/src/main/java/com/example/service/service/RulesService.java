package com.example.service.service;

import com.example.repository.entity.RuleEntity;
import com.example.service.model.Rule;
import com.example.repository.repository.RulesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RulesService {

    private final RulesRepository rulesRepository;

    public RulesService(RulesRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    public Mono<Rule> createRule(Rule rule) {
        var repoRule = new RuleEntity(rule.id(), rule.name(), rule.description());
        return rulesRepository.save(repoRule).map(entity -> new Rule(entity.id(), entity.name(), entity.description()));
    }

    public Mono<Rule> getRule(Long id) {
        return rulesRepository.findById(id)
                .map(entity -> new Rule(entity.id(), entity.name(), entity.description()));
    }

    public Flux<Rule> getAllRules() {
        return rulesRepository.findAll()
                .map(entity -> new Rule(entity.id(), entity.name(), entity.description()));
    }

    public Mono<Rule> updateRule(Rule rule) {
        var repoRule = new RuleEntity(rule.id(), rule.name(), rule.description());
        return rulesRepository.save(repoRule).map(entity -> new Rule(entity.id(), entity.name(), entity.description()));
    }

    public Mono<Void> deleteRule(Long id) {
        return rulesRepository.deleteById(id);
    }
}