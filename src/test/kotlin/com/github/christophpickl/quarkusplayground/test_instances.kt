package com.github.christophpickl.quarkusplayground

val StoreId.Companion.any get() = StoreId(42)

val Store.Companion.any
    get() = Store(
        id = StoreId.any,
        name = "anyName"
    )
