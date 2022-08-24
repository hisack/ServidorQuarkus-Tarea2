package com.programacion.Controller;

import com.programacion.db.Album;
import com.programacion.servicios.AlbumProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlbumController {
    @Autowired
    private AlbumProxy servicioAlbum;

    @GetMapping({"/album", "/"})
    public String ListarAlbums(Model model) {
        model.addAttribute("message", "Lista de Albums");
        model.addAttribute("album", servicioAlbum.albumfindAll());
        return "index";
    }

    @GetMapping("/album/insert")
    public String mostrarFormRegistroAlbum(Model model) {
        Album album = new Album();
        model.addAttribute("album", album);
        return "nuevo_album";
    }

    @PostMapping("/album")
    public String agregarAlbum(@ModelAttribute("album") Album album) {
        servicioAlbum.createAlbum(album);
        return "redirect:/";
    }

    @GetMapping("/album/{id}")
    public String eliminarEstudiante(@PathVariable int id) {
         servicioAlbum.deleteAlbum(id);
        return "redirect:/";
    }

    @GetMapping("/album/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
        modelo.addAttribute("album", servicioAlbum.albumfindById(id));
        return "editar_album";
    }

    @PostMapping("/album/{id}")
    public String actualizarEstudiante(@PathVariable int id, @ModelAttribute("album") Album album,
                                       Model modelo) {
        Album albumExistente = servicioAlbum.albumfindById(id);
        albumExistente.setId(album.getId());
        albumExistente.setTitle(album.getTitle());
        albumExistente.setSinger_id(album.getSinger_id());
        albumExistente.setRelease_date(album.getRelease_date());

        servicioAlbum.updateAlbum(id, albumExistente);
        return "redirect:/";
    }

}
