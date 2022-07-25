package com.github.christophpickl.quarkusplayground.domain

@JvmInline
value class StoreId(val value: Int) {
    companion object;
    init {
        require(value > 0) { "ID must be bigger-equals 1, but was: $value" }
    }
}

data class Store(
    val id: StoreId,
    val name: String
) {
    companion object;
    init {
        require(name.isNotEmpty()) { "Store's name must not be empty!" }
    }
}

data class StoreCreateRequest(
    val name: String
)
