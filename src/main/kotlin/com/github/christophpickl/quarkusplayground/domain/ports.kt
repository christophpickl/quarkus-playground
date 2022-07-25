package com.github.christophpickl.quarkusplayground.domain

interface StoreRepository {
    fun selectAll(): Collection<Store>
    fun selectSingle(id: StoreId): Store?
    fun insert(request: StoreCreateRequest): Store
}
