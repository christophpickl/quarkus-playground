package com.github.christophpickl.quarkusplayground.api

data class ErrorDto(
    val message: String
)

data class StoresDto(
    val stores: List<StoreDto>
)

data class StoreDto(
    val id: Int,
    val name: String
)

data class StoreCreateDto(
    val name: String
)
