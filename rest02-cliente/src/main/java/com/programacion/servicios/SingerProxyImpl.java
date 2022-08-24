package com.programacion.servicios;

import com.programacion.db.Singer;
import com.programacion.servicios.SingerProxy;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SingerProxyImpl implements SingerProxy {


    private SingerProxy proxy;

    public SingerProxyImpl() {
        Client client = ClientBuilder.newBuilder().build();
        ResteasyWebTarget target = (ResteasyWebTarget) client.target("http://localhost:9090/rest02");
        proxy = target.proxy(SingerProxy.class);
    }

    @Override
    public List<Singer> listAll() {
        return proxy.listAll();
    }

    @Override
    public Singer findById(Integer id) {
        return proxy.findById(id);
    }

    @Override
    public Response create(Singer obj) {
        return proxy.create(obj);
    }

    @Override
    public Response update(Integer id, Singer obj) {
        return proxy.update(id, obj);
    }

    @Override
    public Response delete(Integer id) {
        return proxy.delete(id);
    }
}
