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

@Path("/store")
@Produces(MediaType.APPLICATION_JSON)
class StoreResource(
    private val service: StoreService
) {

    @GET
    @Path("/")
    fun getAll(): Response {
        val stores = StoresDto(service.fetchAll().map { it.toStoreDto() })
        return Response.ok(stores).build()
    }

    @GET
    @Path("/{id}")
    fun getSingle(
        @PathParam("id") id: Int
    ): Response {
        val found = service.find(StoreId(id))
        return if (found == null) {
            Response.status(404).build()
        } else {
            Response.ok(found.toStoreDto()).build()
        }
    }

    private fun Store.toStoreDto() = StoreDto(
        id = id.value, name = name
    )
}
