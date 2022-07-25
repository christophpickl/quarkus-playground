package com.github.christophpickl.quarkusplayground.api

import io.quarkus.test.junit.QuarkusTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

@QuarkusTest
class RootResourceTest {
    @Test
    fun `When call root endpoint Then return 200 and proper body`() {
        Given {
            this
        } When {
            get("/")
        } Then {
            statusCode(200)
            contentType(ContentType.TEXT)//("text/plain")) // ";charset=UTF-8"
            body(CoreMatchers.equalTo("Hello Quarkus!"))
        }
    }
}
