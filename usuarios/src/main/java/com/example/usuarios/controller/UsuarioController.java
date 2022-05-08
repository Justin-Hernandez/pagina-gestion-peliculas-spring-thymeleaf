package com.example.usuarios.controller;

import com.example.usuarios.model.ResponseClass;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    IUsuariosService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> buscarTodos(@RequestParam(value = "nombre", required = false) String nombre,
                                     @RequestParam(value = "correo", required = false) String correo) {

        if(nombre == null && correo == null)
        {
            return usuarioService.buscarTodos();
        }else if(nombre == null && correo != null)
        {
            return usuarioService.buscarUsuarioPorCorreo(correo);
        }else if(nombre != null && correo == null)
        {
            return usuarioService.buscarUsuarioPorNombre(nombre);
        }else
        {
            return usuarioService.buscarPorNombreYCorreo(nombre, correo);
        }
    }

    @GetMapping("/login/{mail}/{password}")
    public Object login(@PathVariable(value = "mail") String correo,
                        @PathVariable(value = "password") String password)
    {
        Usuario usuario = usuarioService.buscarPorCorreoYPassword(correo, password);

        return usuario != null ? usuario : new ResponseEntity<ResponseClass>(new ResponseClass("Unauthorized"), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/usuarios/{id}")
    public Object buscarUsuarioPorId(@PathVariable("id") Integer id){

        Usuario usuario = usuarioService.buscarUsuarioPorId(id);

        return usuario != null ? usuario: new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/usuarios")
    public Object guardarUsuario(@RequestBody Usuario usuario){

        Usuario usuario_aux = usuarioService.buscarUsuarioPorId(usuario.getIdUsuario());

        if(usuario_aux == null)
        {
            usuarioService.guardarUsuario(usuario);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }else
        {
            usuario.setIdUsuario(0);
            usuarioService.guardarUsuario(usuario);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }
    }

    @PutMapping("/usuarios")
    public Object actualizarUsuario(@RequestBody Usuario usuario){

        Usuario usuario_aux = usuarioService.buscarUsuarioPorId(usuario.getIdUsuario());

        if(usuario_aux == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Usuario con id: " + usuario.getIdUsuario() + " no existe"), HttpStatus.NOT_FOUND);
        }else {
            usuarioService.actualizarUsuario(usuario);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public Object eliminarUsuario(@PathVariable("id") Integer id){

        Usuario usuario = usuarioService.buscarUsuarioPorId(id);

        if(usuario == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
        }else {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }
}
