package com.github.christophpickl.quarkusplayground

import javax.enterprise.context.ApplicationScoped

@JvmInline
value class FooId(val value: Int)

data class Foo(
    val id: FooId,
    val name: String
)

interface FooService {
    fun find(id: FooId): Foo?
}

//@Traced
//@Transactional(Transactional.TxType.REQUIRED)
@ApplicationScoped
class InMemoryFooService : FooService {
    private val foos = mapOf(
        FooId(1) to Foo(FooId(1), "bar")
    )

    override fun find(id: FooId) = foos[id]
}
