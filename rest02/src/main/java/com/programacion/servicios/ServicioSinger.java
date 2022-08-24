package com.programacion.servicios;

import com.programacion.db.Singer;

import java.util.List;

public interface ServicioSinger {

    List<Singer> findAll();

    Singer findById(Integer id);

    void create(Singer singer);

    void update(Singer singer);

    void delete(Integer id);
}
