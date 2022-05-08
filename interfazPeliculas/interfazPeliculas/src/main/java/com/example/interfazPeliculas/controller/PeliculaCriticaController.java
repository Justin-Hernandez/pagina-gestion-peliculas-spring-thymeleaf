package com.example.interfazPeliculas.controller;

import com.example.interfazPeliculas.model.Critica;
import com.example.interfazPeliculas.model.Pelicula;
import com.example.interfazPeliculas.model.Usuario;
import com.example.interfazPeliculas.paginator.PageRender;
import com.example.interfazPeliculas.service.InterfaceCriticaService;
import com.example.interfazPeliculas.service.InterfacePeliculaService;
import com.example.interfazPeliculas.service.InterfaceUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Date;

@Controller
@RequestMapping("/peliculas")
public class PeliculaCriticaController {

    @Autowired
    InterfacePeliculaService peliculasService;

    @Autowired
    InterfaceCriticaService critiasService;

    @Autowired
    InterfaceUsuarioService usuariosService;

    @GetMapping("/new")
    public String insertarPelicula(String pais, String nombre, Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        return "pelicula/insertPelicula";
    }

    @PostMapping("/guardar")
    public String guardarPelicula(Model model, Pelicula pelicula, RedirectAttributes attributes) {

        pelicula.setIdPelicula(0);
        pelicula.setPortada("");
        peliculasService.insertarPelicula(pelicula);
        attributes.addFlashAttribute("msg", "La pelicula ha sido guardada correctamente");
        return "redirect:/home";
    }

    @GetMapping("/")
    public String listarPeliculas(@RequestParam(name="titulo", required = false) String titulo,
                                  @RequestParam(name="genero", required = false) String genero,
                                  @RequestParam(name="actor", required = false) String actor,
                                  @RequestParam(name="page", defaultValue="0") int page,
                                  Model model){
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listado = peliculasService.buscarPeliculas(titulo, genero, actor, pageable);
        PageRender<Pelicula> pageRender = new PageRender<Pelicula>("/peliculas/", listado);

        model.addAttribute("listadoPeliculas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/listadoPeliculas";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarPelicula(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        peliculasService.eliminarPelicula(id);
        attributes.addFlashAttribute("msg", "La pelicula fue borrada del sistema");
        return "redirect:/peliculas/";
    }

    @GetMapping("/{id}/criticas")
    public String listarCriticas(@PathVariable("id") Integer idPelicula,
                                 @RequestParam(name="page", defaultValue="0") int page,
                                 Model model)
    {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Critica> listado = critiasService.buscarCriticasPorIdPelicula(idPelicula, pageable);
        PageRender<Critica> pageRender = new PageRender<Critica>("/peliculas/" + idPelicula + "/criticas", listado);

        Critica critica = new Critica();

        int acumulador = 0;

        for(Critica c : listado.getContent() )
        {
            acumulador += c.getNota();
        }

        DecimalFormat value = new DecimalFormat("#.#");

        String v = "-";

        if(listado.getContent().size() != 0)
        {
            v = value.format(acumulador/listado.getContent().size());
        }

        model.addAttribute("media", v);
        model.addAttribute("critica", critica);
        model.addAttribute("tituloPelicula", peliculasService.findById(idPelicula).getTitulo());
        model.addAttribute("listadoCriticas", listado);
        model.addAttribute("page", pageRender);
        return "pelicula/criticas";
    }

    @GetMapping("/{id}/criticas/{id2}")
    public String listarCriticas(@PathVariable("id") Integer idPelicula,
                                 @PathVariable("id2") Integer idCritica,
                                 RedirectAttributes attributes)
    {
        critiasService.eliminarCritica(idCritica);
        attributes.addFlashAttribute("msg", "Critica borrada correctamente");

        return "redirect:/peliculas/" + idPelicula + "/criticas";
    }

    @PostMapping("/{id}/criticas/guardar")
    public String listarCriticas(@PathVariable("id") Integer idPelicula,
                                 Critica critica,
                                 Principal principal)
    {
        critica.setIdCritica(0);
        critica.setFecha(new Date());
        critica.setIdPelicula(idPelicula);

        Usuario usuario = usuariosService.buscarUsuarioPorCorreo(principal.getName());

        critica.setUsuario(usuario);

        critiasService.guardarCritica(critica);

        return "redirect:/peliculas/" + idPelicula + "/criticas";
    }


}
