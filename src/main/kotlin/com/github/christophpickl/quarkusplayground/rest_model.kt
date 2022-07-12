package com.github.christophpickl.quarkusplayground

data class StoresDto(
    val stores: List<StoreDto>
)

data class StoreDto(
    val id: Int, val name: String
)
