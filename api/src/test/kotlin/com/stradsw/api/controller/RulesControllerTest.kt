package com.stradsw.api.controller

import com.stradsw.api.model.RulesRequest
import com.stradsw.api.model.RulesResponse
import com.stradsw.service.model.Rule
import com.stradsw.service.service.RulesService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest(RulesController::class)
class RulesControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var rulesService: RulesService

    @Test
    fun `createRule should return created rule`() {
        val request = RulesRequest("Test Rule", "Description")
        val rule = Rule(1L, "Test Rule", "Description")
        `when`(rulesService.createRule(any(Rule::class.java))).thenReturn(Mono.just(rule))

        webTestClient.post()
            .uri("/rules")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectBody(RulesResponse::class.java)
            .isEqualTo(RulesResponse(1L, "Test Rule", "Description"))
    }

    @Test
    fun `getRule should return rule`() {
        val rule = Rule(1L, "Test Rule", "Description")
        `when`(rulesService.getRule(1L)).thenReturn(Mono.just(rule))

        webTestClient.get()
            .uri("/rules/1")
            .exchange()
            .expectStatus().isOk
            .expectBody(RulesResponse::class.java)
            .isEqualTo(RulesResponse(1L, "Test Rule", "Description"))
    }

    @Test
    fun `getAllRules should return all rules`() {
        val rule1 = Rule(1L, "Rule 1", "Description 1")
        val rule2 = Rule(2L, "Rule 2", "Description 2")
        `when`(rulesService.getAllRules()).thenReturn(Flux.just(rule1, rule2))

        webTestClient.get()
            .uri("/rules")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(RulesResponse::class.java)
            .hasSize(2)
            .contains(
                RulesResponse(1L, "Rule 1", "Description 1"),
                RulesResponse(2L, "Rule 2", "Description 2")
            )
    }

    @Test
    fun `updateRule should return updated rule`() {
        val request = RulesRequest("Updated Rule", "Updated Description")
        val rule = Rule(1L, "Updated Rule", "Updated Description")
        `when`(rulesService.updateRule(any(Rule::class.java))).thenReturn(Mono.just(rule))

        webTestClient.put()
            .uri("/rules/1")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody(RulesResponse::class.java)
            .isEqualTo(RulesResponse(1L, "Updated Rule", "Updated Description"))
    }

    @Test
    fun `deleteRule should return no content`() {
        `when`(rulesService.deleteRule(1L)).thenReturn(Mono.empty())

        webTestClient.delete()
            .uri("/rules/1")
            .exchange()
            .expectStatus().isNoContent
    }
}