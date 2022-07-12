package com.github.christophpickl.quarkusplayground

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test

@QuarkusTest
class RootResourceTest {
    @Test
    fun `When call root endpoint Then return 200 and proper body`() {
        val response = given().`when`().get("/")

//        response.then()
//            .statusCode(200)
        assertThat(response.statusCode, equalTo(200))
        assertThat(response.contentType, equalTo("text/plain;charset=UTF-8"))
        assertThat(response.asString(), equalTo("Hello Quarkus!"))
    }
}
