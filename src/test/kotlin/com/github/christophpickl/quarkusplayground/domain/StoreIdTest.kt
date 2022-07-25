package com.github.christophpickl.quarkusplayground.domain

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StoreIdTest {
    @Test
    fun `When instantiate with valid value Then return`() {
        val id = StoreId(1)

        assertThat(id.value, equalTo(1))
    }

    @Test
    fun `When instantiate with invalid value Then throw`() {
        assertThrows<java.lang.IllegalArgumentException> {
            StoreId(-1)
        }
    }
}