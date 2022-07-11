package com.github.christophpickl.quarkusplayground

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class FooResourceTest {

    // FIXME @InjectMock

    @Test
    fun `When get foo Then return json`() {
        given().`when`().get("/foo/1")

            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(1))
            .body("name", equalTo("bar"))
    }
}
