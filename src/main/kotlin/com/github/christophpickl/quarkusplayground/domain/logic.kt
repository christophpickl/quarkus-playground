package com.github.christophpickl.quarkusplayground.domain

import javax.enterprise.context.ApplicationScoped

interface StoreService {
    fun fetchAll(): Collection<Store>
    fun find(id: StoreId): Store?
    fun save(request: StoreCreateRequest): Store
}

//@Traced
//@Transactional(Transactional.TxType.REQUIRED)
@ApplicationScoped
class StoreServiceImpl(
    private val repo: StoreRepository
) : StoreService {
    override fun fetchAll() = repo.selectAll()
    override fun find(id: StoreId) = repo.selectSingle(id)
    override fun save(request: StoreCreateRequest): Store {
        // FIXME validation; reuse rules from domain-model instantiation
        return repo.insert(request)
    }
}
