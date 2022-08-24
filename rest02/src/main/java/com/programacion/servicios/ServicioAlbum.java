package com.programacion.servicios;

import com.programacion.db.Album;
import jdk.jfr.Name;

import java.util.List;

public interface ServicioAlbum {

     List<Album> albumfindAll();

     Album albumfindById(Integer id);

     void createAlbum(Album album);

     void updateAlbum(Integer id,Album album);

     void deleteAlbum(Integer id);
}
