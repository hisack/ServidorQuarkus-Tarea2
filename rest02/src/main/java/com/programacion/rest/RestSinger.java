package com.programacion.rest;

import com.programacion.db.Album;
import com.programacion.db.Singer;
import com.programacion.servicios.ServicioSinger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/singers")
@ApplicationScoped
public class RestSinger {

    //DI
    @Inject
    private ServicioSinger servicio;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        List<Singer> s = servicio.findAll();

        if (!s.isEmpty()) {
            return Response.status(Response.Status.OK).entity(s).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        Singer s = servicio.findById(id);
        if (s == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(s).build();
    };

    //insertar cantante
    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertar(Singer s) {
        servicio.create(s);
        return Response.status(Response.Status.CREATED).entity(s).build();
    }

    //actualizar cantante
    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPersona( Singer s) {
        servicio.update(s);
        return Response.status(Response.Status.OK).entity(s).build();
    }

    //borrar cantante
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        servicio.delete(id);
        return Response.status(200).entity("Fila Borrada").build();
    }




}
