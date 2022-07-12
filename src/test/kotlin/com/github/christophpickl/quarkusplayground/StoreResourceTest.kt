package com.github.christophpickl.quarkusplayground

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@QuarkusTest
class StoreResourceTest {

    @InjectMock
    lateinit var storeService: StoreService
    private val store = Store.any
    private val nonExistingStoreId = StoreId(666)

    @Test
    fun `When get stores Then return ok json`() {
        whenever(storeService.fetchAll()).thenReturn(emptyList())

        val response = given().`when`().get("/store")

        verify(storeService).fetchAll()
        response.then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("stores", equalTo(emptyList<StoreDto>()))
    }

    @Test
    fun `When get existing store Then return ok json`() {
        whenever(storeService.find(store.id)).thenReturn(store)

        val response = given().`when`().get("/store/${store.id.value}")

        verify(storeService).find(store.id)
        response.then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(store.id.value))
            .body("name", equalTo(store.name))
    }

    @Test
    fun `When get non-existing store Then return 404`() {
        whenever(storeService.find(nonExistingStoreId)).thenReturn(null)

        val response = given().`when`().get("/store/${nonExistingStoreId.value}")

        verify(storeService).find(nonExistingStoreId)
        response.then()
            .statusCode(404)
        // TODO .contentType(ContentType.JSON)
    }
}
