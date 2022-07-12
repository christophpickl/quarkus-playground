package com.github.christophpickl.quarkusplayground

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class FooResourceTest {

    // FIXME @InjectMock

    @Test
    fun `When get existing foo Then return ok json`() {
        given().`when`().get("/foo/1")

            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(1))
            .body("name", equalTo("bar"))
    }

    @Test
    fun `When get non-existing foo Then return 404`() {
        given().`when`().get("/foo/42")

            .then()
            .statusCode(404)
        // TODO .contentType(ContentType.JSON)
    }
}
