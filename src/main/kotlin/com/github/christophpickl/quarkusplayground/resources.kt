package com.github.christophpickl.quarkusplayground

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class RootResource {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    fun sayHello() = "Hello Quarkus!"
}

@Path("/foo")
@Produces(MediaType.APPLICATION_JSON)
class FooResource {
    @Path("/{id}")
    fun findFoo(
        @PathParam("id") id: Int
    ) = Foo(id,"bar")
}

data class Foo(
    val id: Int,
    val name: String
)
