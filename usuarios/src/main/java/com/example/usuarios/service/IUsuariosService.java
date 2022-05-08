package com.example.usuarios.service;

import com.example.usuarios.model.Usuario;

import java.util.List;

public interface IUsuariosService {

    List<Usuario> buscarTodos();

    Usuario buscarUsuarioPorId(Integer idUsuario);

    List<Usuario> buscarUsuarioPorNombre(String nombre);
    List<Usuario> buscarUsuarioPorCorreo(String correo);
    List<Usuario> buscarPorNombreYCorreo(String nombre, String correo);

    Usuario buscarPorCorreoYPassword(String correo, String password);

    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Integer idUsuario);
    void actualizarUsuario(Usuario usuario);
}
