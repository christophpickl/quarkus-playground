package com.github.christophpickl.quarkusplayground.api

import com.github.christophpickl.quarkusplayground.domain.Store
import com.github.christophpickl.quarkusplayground.domain.StoreCreateRequest
import com.github.christophpickl.quarkusplayground.domain.StoreId
import com.github.christophpickl.quarkusplayground.domain.StoreService
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

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
        // FIXME test when sending invalid ID here (should be a 400 bad request, but need translation of IllegalArg to...)
        val found = service.find(StoreId(id))
        return if (found == null) {
            Response.status(404).entity(ErrorDto("Could not find store by ID: $id")).build()
        } else {
            Response.ok(found.toStoreDto()).build()
        }
    }

    // FIXME test this
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    fun create(request: StoreCreateDto): Response {
        val store = service.save(request.toStoreCreateRequest())
        return Response.ok(store.toStoreDto()).build()
    }

    private fun Store.toStoreDto() = StoreDto(
        id = id.value, name = name
    )

    private fun StoreCreateDto.toStoreCreateRequest() = StoreCreateRequest(
        name = name
    )
}

