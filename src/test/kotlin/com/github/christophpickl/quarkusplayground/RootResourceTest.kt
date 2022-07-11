package com.github.christophpickl.quarkusplayground

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.parsing.Parser
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.startsWith
import org.junit.jupiter.api.Test

@QuarkusTest
class RootResourceTest {

    @Test
    fun `When call root endpoint Then return 200 and proper body`() {
        // FIXME text plain parser won't work?!
        RestAssured.registerParser("text/plain", Parser.TEXT)
        given()
            .`when`()
            .contentType(ContentType.TEXT)
            .accept(ContentType.TEXT)
            .get("/")
            .then()
            .statusCode(200)
            .contentType(ContentType.TEXT)
            .body("message", equalTo("hello"))
    }
}
