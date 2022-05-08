package com.example.interfazPeliculas.controller;

import com.example.interfazPeliculas.model.Actor;
import com.example.interfazPeliculas.paginator.PageRender;
import com.example.interfazPeliculas.service.InterfaceActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/actores")
public class ActorController {

    @Autowired
    InterfaceActorService actoresService;

    @GetMapping("/new")
    public String insertarActor(String pais, String nombre, Model model){
        Actor actor = new Actor();
        model.addAttribute("actor", actor);
        return "actor/insertActor";
    }

    @PostMapping("/guardar")
    public String guardarActor(Model model, Actor actor, RedirectAttributes attributes) {

        actor.setIdActor(0);
        actoresService.insertarActor(actor);
        attributes.addFlashAttribute("msg", "El actor ha sido guardado correctamente");
        return "redirect:/home";
    }

    @GetMapping("/")
    public String listarActores(@RequestParam(name="nombre", required = false) String nombre,
                                @RequestParam(name="pais", required = false) String pais,
                                @RequestParam(name="page", defaultValue="0") int page,
                                Model model){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado = actoresService.buscarActores(nombre, pais, pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/actores/", listado);

        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actor/listadoActores";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarActor(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        actoresService.eliminarActro(id);
        attributes.addFlashAttribute("msg", "El actor fue borrado del sistema");
        return "redirect:/actores/";
    }
}
