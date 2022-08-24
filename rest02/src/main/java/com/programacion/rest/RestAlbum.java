package com.programacion.rest;

import com.programacion.db.Album;
import com.programacion.servicios.ServicioAlbum;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/album")
@ApplicationScoped
public class RestAlbum {

    @Inject
    private ServicioAlbum servicioAlbum;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Album> albumfindAll() {

        List<Album> album = servicioAlbum.albumfindAll();
/*
        if (!album.isEmpty()) {
            return Response.status(Response.Status.OK).entity(album).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }*/

        return album;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Album albumfindById(@PathParam("id") Integer id) {

        Album album = servicioAlbum.albumfindById(id);
/*
        if (album.getId() != null) {
            return Response.status(Response.Status.OK).entity(album).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }*/

        return album;
    }


    @POST
   // @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAlbum(Album a) {
         servicioAlbum.createAlbum(a);

            return Response.status(Response.Status.CREATED).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAlbum(@PathParam("id") Integer id, Album album){
        servicioAlbum.updateAlbum(id, album);
        return Response.status(Response.Status.OK).entity(album).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAlbum(@PathParam("id") Integer id) {
       servicioAlbum.deleteAlbum(id);
            return Response.status(200).entity("Fila Borrada").build();
    }
}
