package com.programacion.servicios;


import com.programacion.db.Singer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/singers")
public interface SingerProxy {

    /* GET: /albums - listar todos los albums */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Singer> listAll();

    /* GET: /singers/{id} - buscar un album */
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Singer findById(@PathParam("id") Integer id);


    /* POST: /singers - crear un nuevo album */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response create(Singer obj);

    /* PUT: /singers/{id} - actualizar un album */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(@PathParam("id") Integer id, Singer obj);

    /* DELETE: /singers/{id} - eliminar un album */
    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id);

}
