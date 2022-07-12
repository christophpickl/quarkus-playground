package com.github.christophpickl.quarkusplayground

@JvmInline
value class StoreId(val value: Int) {
    companion object
}

data class Store(
    val id: StoreId,
    val name: String
) {
    companion object
}
