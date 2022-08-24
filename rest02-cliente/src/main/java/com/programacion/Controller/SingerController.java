package com.programacion.Controller;

import com.programacion.db.Singer;
import com.programacion.servicios.SingerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SingerController {

    @Autowired
    private SingerProxy servicioSinger;


    /*
     * Request mapping:  guides the DispatcherServlet which controller method it
     * needs to invoke as a response to the request based on the request path.
     */
    @GetMapping({"/singers" })
    public String ListarSingers(Model model) {
        model.addAttribute("message", "Lista de Singers");
        model.addAttribute("singers", servicioSinger.listAll());
        return "singers";
    }

    @GetMapping("/singers/nuevo")
    public String mostrarFormRegistroAlbum(Model model) {
        Singer singer = new Singer();
        model.addAttribute("singer", singer);
        return "nuevo_singer";
    }

    @PostMapping("/singers")
    public String agregarSinger(@ModelAttribute("album") Singer singer) {
        servicioSinger.create(singer);
        return "redirect:/singers";
    }

    @GetMapping("/singers/{id}")
    public String eliminarSinger(@PathVariable int id) {
        servicioSinger.delete(id);
        return "redirect:/singers";
    }

    @GetMapping("/singers/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
        modelo.addAttribute("singer", servicioSinger.findById(id));
        return "editar_singer";
    }

    @PostMapping("/singers/{id}")
    public String actualizarAlbum(@PathVariable int id, @ModelAttribute("album") Singer singer,
                                  Model modelo) {
        Singer singerExistente = servicioSinger.findById(id);
        singerExistente.setId(singer.getId());
        singerExistente.setFirstName(singer.getFirstName());
        singerExistente.setLastName(singer.getLastName());
        singerExistente.setBirthDate(singer.getBirthDate());

        servicioSinger.update(id, singerExistente);
        return "redirect:/singers";
    }

}
