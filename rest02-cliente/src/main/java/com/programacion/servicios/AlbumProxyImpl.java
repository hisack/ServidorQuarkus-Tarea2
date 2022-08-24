package com.programacion.servicios;

import com.programacion.db.Album;
import com.programacion.db.Singer;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumProxyImpl implements AlbumProxy{


    private AlbumProxy proxy;

    public AlbumProxyImpl() {
        Client client = ClientBuilder.newBuilder().build();
        ResteasyWebTarget target = (ResteasyWebTarget) client.target("http://localhost:8080");
        proxy = target.proxy(AlbumProxy.class);
    }

    @Override
    public List<Album> albumfindAll() {
        return proxy.albumfindAll();
    }

    @Override
    public Album albumfindById(Integer id) {
        return proxy.albumfindById(id);
    }

    @Override
    public Response createAlbum(Album obj) {
        return proxy.createAlbum(obj);
    }

    @Override
    public Response updateAlbum(Integer id, Album obj) {
        return proxy.updateAlbum(id, obj);
    }

    @Override
    public Response deleteAlbum(Integer id) {
        return proxy.deleteAlbum(id);
    }

    @Override
    public List<Singer> findAll() { return proxy.findAll();}

}
