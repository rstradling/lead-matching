package com.example.api.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.api.model.RulesRequest;
import com.example.api.model.RulesResponse;
import com.example.service.model.Rule;
import com.example.service.service.RulesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(RulesController.class)
class RulesControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RulesService rulesService;

    @Test
    void createRule_shouldReturnCreatedRule() {
        RulesRequest request = new RulesRequest("Test Rule", "Description");
        Rule rule = new Rule(1L, "Test Rule", "Description");
        when(rulesService.createRule(any(Rule.class))).thenReturn(Mono.just(rule));

        webTestClient.post()
                .uri("/rules")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(RulesResponse.class)
                .isEqualTo(new RulesResponse(1L, "Test Rule", "Description"));
    }

    @Test
    void getRule_shouldReturnRule() {
        Rule rule = new Rule(1L, "Test Rule", "Description");
        when(rulesService.getRule(1L)).thenReturn(Mono.just(rule));

        webTestClient.get()
                .uri("/rules/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(RulesResponse.class)
                .isEqualTo(new RulesResponse(1L, "Test Rule", "Description"));
    }

    @Test
    void getAllRules_shouldReturnAllRules() {
        Rule rule1 = new Rule(1L, "Rule 1", "Description 1");
        Rule rule2 = new Rule(2L, "Rule 2", "Description 2");
        when(rulesService.getAllRules()).thenReturn(Flux.just(rule1, rule2));

        webTestClient.get()
                .uri("/rules")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RulesResponse.class)
                .hasSize(2)
                .contains(
                        new RulesResponse(1L, "Rule 1", "Description 1"),
                        new RulesResponse(2L, "Rule 2", "Description 2")
                );
    }

    @Test
    void updateRule_shouldReturnUpdatedRule() {
        RulesRequest request = new RulesRequest("Updated Rule", "Updated Description");
        Rule rule = new Rule(1L, "Updated Rule", "Updated Description");
        when(rulesService.updateRule(any(Rule.class))).thenReturn(Mono.just(rule));

        webTestClient.put()
                .uri("/rules/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RulesResponse.class)
                .isEqualTo(new RulesResponse(1L, "Updated Rule", "Updated Description"));
    }

    @Test
    void deleteRule_shouldReturnNoContent() {
        when(rulesService.deleteRule(1L)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/rules/1")
                .exchange()
                .expectStatus().isNoContent();
    }
}
