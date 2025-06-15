package com.stradsw.api.controller

import com.stradsw.api.model.RulesRequest
import com.stradsw.api.model.RulesResponse
import com.stradsw.service.model.Rule
import com.stradsw.service.service.RulesService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/rules")
class RulesController(private val rulesService: RulesService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRule(@Valid @RequestBody request: RulesRequest): Mono<RulesResponse> {
        return rulesService.createRule(Rule(null, request.name, request.description))
            .map { RulesResponse(it.id, it.name, it.description) }
    }

    @GetMapping("/{id}")
    fun getRule(@PathVariable id: Long): Mono<RulesResponse> {
        return rulesService.getRule(id)
            .map { RulesResponse(it.id, it.name, it.description) }
    }

    @GetMapping
    fun getAllRules(): Flux<RulesResponse> {
        return rulesService.getAllRules()
            .map { RulesResponse(it.id, it.name, it.description) }
    }

    @PutMapping("/{id}")
    fun updateRule(@PathVariable id: Long, @Valid @RequestBody request: RulesRequest): Mono<RulesResponse> {
        return rulesService.updateRule(Rule(id, request.name, request.description))
            .map { RulesResponse(it.id, it.name, it.description) }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRule(@PathVariable id: Long): Mono<Void> {
        return rulesService.deleteRule(id)
    }
}