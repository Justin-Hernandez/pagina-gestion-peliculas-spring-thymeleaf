package com.example.interfazPeliculas.controller;

import com.example.interfazPeliculas.model.Actor;
import com.example.interfazPeliculas.model.Usuario;
import com.example.interfazPeliculas.service.InterfaceActorService;
import com.example.interfazPeliculas.service.InterfaceUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal) {

        if (principal != null) {
            return "redirect:/home";
        }

        if (error != null) {
            model.addAttribute("msg","Usuario o contraseña incorrecta");
        }

        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model, RedirectAttributes attributes)
    {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "signup";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
