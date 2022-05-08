package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterfaceUsuarioService {

    Page<Usuario> buscarUsuarios(String nombre, String correo, Pageable pageable);
    Usuario buscarUsuarioPorCorreo(String correo);
    Usuario buscarUsuarioPorId(Integer idUsuario);
    Usuario login(String correo, String password);

    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Integer idUsuario);
    void actualizarUsuario(Usuario usuario);
}
