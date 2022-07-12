package com.github.christophpickl.quarkusplayground

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class StoreResourceTest {

    // FIXME @InjectMock

    @Test
    fun `When get existing store Then return ok json`() {
        given().`when`().get("/store/1")

            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(1))
            .body("name", equalTo("bar"))
    }

    @Test
    fun `When get non-existing store Then return 404`() {
        given().`when`().get("/store/42")

            .then()
            .statusCode(404)
        // TODO .contentType(ContentType.JSON)
    }
}
