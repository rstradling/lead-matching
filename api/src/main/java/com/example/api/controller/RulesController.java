package com.example.api.controller;

import com.example.api.model.RulesRequest;
import com.example.api.model.RulesResponse;
import com.example.service.model.Rule;
import com.example.service.service.RulesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rules")
public class RulesController {

    private final RulesService rulesService;

    public RulesController(RulesService rulesService) {
        this.rulesService = rulesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RulesResponse> createRule(@Valid @RequestBody RulesRequest request) {
        return rulesService.createRule(new Rule(null, request.name(), request.description()))
                .map(rule -> new RulesResponse(rule.id(), rule.name(), rule.description()));
    }

    @GetMapping("/{id}")
    public Mono<RulesResponse> getRule(@PathVariable Long id) {
        return rulesService.getRule(id)
                .map(rule -> new RulesResponse(rule.id(), rule.name(), rule.description()));
    }

    @GetMapping
    public Flux<RulesResponse> getAllRules() {
        return rulesService.getAllRules()
                .map(rule -> new RulesResponse(rule.id(), rule.name(), rule.description()));
    }

    @PutMapping("/{id}")
    public Mono<RulesResponse> updateRule(@PathVariable Long id, @Valid @RequestBody RulesRequest request) {
        return rulesService.updateRule(new Rule(id, request.name(), request.description()))
                .map(rule -> new RulesResponse(rule.id(), rule.name(), rule.description()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRule(@PathVariable Long id) {
        return rulesService.deleteRule(id);
    }
}
