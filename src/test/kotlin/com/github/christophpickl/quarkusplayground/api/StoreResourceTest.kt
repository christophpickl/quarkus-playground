package com.github.christophpickl.quarkusplayground.api

import com.github.christophpickl.quarkusplayground.domain.Store
import com.github.christophpickl.quarkusplayground.domain.StoreId
import com.github.christophpickl.quarkusplayground.domain.StoreService
import com.github.christophpickl.quarkusplayground.domain.any
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@QuarkusTest
class StoreResourceTest {

    @InjectMock
    private lateinit var storeService: StoreService
    private val store = Store.any
    private val nonExistingStoreId = StoreId(666)

    @Test
    fun `When get stores Then return ok json`() {
        Given {
            whenever(storeService.fetchAll()).thenReturn(emptyList())
            this
        } When {
            get("/store")
        } Then {
            verify(storeService).fetchAll()
            statusCode(200)
            contentType(ContentType.JSON)
            body("stores", equalTo(emptyList<StoreDto>()))
        }
    }

    @Test
    fun `When get existing store Then return ok json`() {
        Given {
            whenever(storeService.find(store.id)).thenReturn(store)
            this
        } When {
            get("/store/${store.id.value}")
        } Then {
            verify(storeService).find(store.id)
            statusCode(200)
            contentType(ContentType.JSON)
            body("id", equalTo(store.id.value))
            body("name", equalTo(store.name))
        }
    }

    @Test
    fun `When get non-existing store Then return 404`() {
        Given {
            whenever(storeService.find(nonExistingStoreId)).thenReturn(null)
            this
        } When {
            get("/store/${nonExistingStoreId.value}")
        } Then {
            verify(storeService).find(nonExistingStoreId)
            statusCode(404)
            contentType(ContentType.JSON)
            body("message", containsString(nonExistingStoreId.value.toString()))
        }
    }
}
