package com.programacion.servicios;

import com.programacion.db.Album;
import com.programacion.db.Singer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/album")
public interface AlbumProxy {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Album> albumfindAll();

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Album albumfindById(@PathParam("id") Integer id);

    @POST
    //@Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response createAlbum(Album obj);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateAlbum(@PathParam("id") Integer id, Album obj);

    @DELETE
    @Path("/{id}")
    Response deleteAlbum(@PathParam("id") Integer id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Singer> findAll();








}
