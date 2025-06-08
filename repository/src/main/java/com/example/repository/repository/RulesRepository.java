package com.example.repository.repository;

import com.example.repository.entity.RuleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RulesRepository extends ReactiveCrudRepository<RuleEntity, Long> {

    default Mono<RuleEntity> save(RuleEntity rule) {
        return save(new RuleEntity(rule.id(), rule.name(), rule.description()))
                .map(entity -> new RuleEntity(entity.id(), entity.name(), entity.description()));
    }

    default Mono<RuleEntity> findById(Long id) {
        return findById(id)
                .map(entity -> new RuleEntity(entity.id(), entity.name(), entity.description()));
    }

    default Flux<RuleEntity> findAll() {
        return findAll()
                .map(entity -> new RuleEntity(entity.id(), entity.name(), entity.description()));
    }

    default Mono<Void> deleteById(Long id) {
        return deleteById(id);
    }
}