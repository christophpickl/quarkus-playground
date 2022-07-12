package com.github.christophpickl.quarkusplayground

import javax.enterprise.context.ApplicationScoped

interface StoreService {
    fun fetchAll(): Collection<Store>
    fun find(id: StoreId): Store?
}

//@Traced
//@Transactional(Transactional.TxType.REQUIRED)
@ApplicationScoped
class InMemoryStoreService : StoreService {

    private val stores = listOf(
        Store(StoreId(1), "foo"),
        Store(StoreId(2), "bar")
    ).associateBy { it.id }

    override fun fetchAll() = stores.values

    override fun find(id: StoreId) = stores[id]
}
