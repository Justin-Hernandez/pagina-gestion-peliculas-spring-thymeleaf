package com.example.interfazPeliculas.controller;

import com.example.interfazPeliculas.model.Critica;
import com.example.interfazPeliculas.model.Pelicula;
import com.example.interfazPeliculas.model.Rol;
import com.example.interfazPeliculas.model.Usuario;
import com.example.interfazPeliculas.paginator.PageRender;
import com.example.interfazPeliculas.service.InterfaceUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    InterfaceUsuarioService usuarioService;

    @PostMapping("/usuarios/guardar")
    public String guardarActor(Model model, Usuario usuario, RedirectAttributes attributes) {

        usuario.setIdUsuario(0);
        usuario.setEnable((byte) 1);

        List<Critica> criticas = new ArrayList<>();
        usuario.setCriticas(criticas);

        List<Rol> roles = new ArrayList<>();
        roles.add(new Rol(2, "ROLE_USER"));
        usuario.setRoles(roles);

        usuarioService.guardarUsuario(usuario);
        attributes.addFlashAttribute("msg", "Cuenta creada correctamente");

        return "redirect:/home";
    }

    @GetMapping("/usuarios/borrar/{id}")
    public String eliminarUsuario(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        usuarioService.eliminarUsuario(id);
        attributes.addFlashAttribute("msg", "El usuario fue borrado del sistema");
        return "redirect:/usuarios/";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(@RequestParam(name="nombre", required = false) String nombre,
                                 @RequestParam(name="correo", required = false) String correo,
                                 @RequestParam(name="page", defaultValue="0") int page,
                                 Model model){

        Pageable pageable = PageRequest.of(page, 5);
        Page<Usuario> listado = usuarioService.buscarUsuarios(nombre, correo, pageable);
        PageRender<Usuario> pageRender = new PageRender<Usuario>("/usuarios", listado);

        model.addAttribute("listadoUsuarios", listado);
        model.addAttribute("page", pageRender);
        return "usuario/listadoUsuarios";
    }
}

