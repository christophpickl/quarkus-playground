package com.github.christophpickl.quarkusplayground.domain

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StoreTest {
    @Test
    fun `When instantiate with valid name Then return it`() {
        val store = Store(StoreId.any, "name")

        assertThat(store.name, equalTo("name"))
    }

    @Test
    fun `When instantiate with invalid name Then throw`() {
        assertThrows<IllegalArgumentException> {
            Store(StoreId.any, "")
        }
    }
}
