package com.example.usuarios.service;

import com.example.usuarios.DAO.IUsuarioDAO;
import com.example.usuarios.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplementacion implements IUsuariosService{

    @Autowired
    IUsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioDAO.buscarTodos();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        return usuarioDAO.buscarUsuarioPorId(idUsuario);
    }

    @Override
    public List<Usuario> buscarUsuarioPorNombre(String nombre) {
        return usuarioDAO.buscarUsuarioPorNombre(nombre);
    }

    @Override
    public List<Usuario> buscarUsuarioPorCorreo(String correo) {
        return usuarioDAO.buscarUsuarioPorCorreo(correo);
    }

    @Override
    public List<Usuario> buscarPorNombreYCorreo(String nombre, String correo) {
        return usuarioDAO.buscarPorNombreYCorreo(nombre, correo);
    }

    @Override
    public Usuario buscarPorCorreoYPassword(String correo, String password) {
        return usuarioDAO.buscarPorCorreoYPassword(correo, password);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.guardarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
        usuarioDAO.eliminarUsuario(idUsuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }

}
