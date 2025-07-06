package com.stradsw.api.config

import com.stradsw.repository.repository.RulesRepository
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.mockito.Mockito.mock

@TestConfiguration
class TestConfig {
    
    @Bean
    @Primary
    fun rulesRepository(): RulesRepository {
        return mock(RulesRepository::class.java)
    }
}