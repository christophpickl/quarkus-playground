package com.github.christophpickl.quarkusplayground.adapter

import com.github.christophpickl.quarkusplayground.domain.Store
import com.github.christophpickl.quarkusplayground.domain.StoreCreateRequest
import com.github.christophpickl.quarkusplayground.domain.StoreId
import com.github.christophpickl.quarkusplayground.domain.StoreRepository
import java.util.concurrent.atomic.AtomicInteger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class InMemoryStoreRepository : StoreRepository {

    private val idCounter = AtomicInteger(1)
    private val stores: MutableMap<StoreId, Store> = listOf(
        Store(StoreId(idCounter.getAndIncrement()), "foo"),
        Store(StoreId(idCounter.getAndIncrement()), "bar")
    ).associateBy { it.id }.toMutableMap()

    override fun selectAll() = stores.values

    override fun selectSingle(id: StoreId) = stores[id]
    override fun insert(request: StoreCreateRequest): Store {
        val store = request.toStore()
        // FIXME disable overwrite!
        stores[store.id] = store
        return store
    }

    private fun StoreCreateRequest.toStore() =
        Store(StoreId(idCounter.getAndIncrement()), name)
}
