package com.github.christophpickl.quarkusplayground.api

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class RootResource {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    fun sayHello() = "Hello Quarkus!"
}