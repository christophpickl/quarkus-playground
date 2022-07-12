package com.github.christophpickl.quarkusplayground

@JvmInline
value class StoreId(val value: Int)

data class Store(
    val id: StoreId,
    val name: String
)
