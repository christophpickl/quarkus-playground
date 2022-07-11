package com.github.christophpickl.quarkusplayground

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.inject.Singleton

@Singleton
class JacksonCustomizer : ObjectMapperCustomizer {
    override fun customize(objectMapper: ObjectMapper) {
        objectMapper
            .registerModule(KotlinModule.Builder().build())
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}
