package com.github.christophpickl.quarkusplayground

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/")
class RootResource {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    fun sayHello() = "Hello Quarkus!"
}

@Path("/foo")
@Produces(MediaType.APPLICATION_JSON)
class FooResource(
    private val service: FooService
) {

    @GET
    @Path("/{id}")
    fun getSingle(
        @PathParam("id") id: Int
    ) = (service.find(FooId(id))?.let { found ->
        Response.ok(found.toFooDto())
    } ?: Response.status(404)).build()
}

private fun Foo.toFooDto() = FooDto(
    id = id.value, name = name
)

data class FooDto(
    val id: Int, val name: String
)
