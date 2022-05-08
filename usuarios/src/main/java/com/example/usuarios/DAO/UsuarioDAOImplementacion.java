package com.example.usuarios.DAO;

import com.example.usuarios.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAOImplementacion implements IUsuarioDAO{

    @Autowired
    IUsuarioJPA usuarioJPA;

    @Autowired
    ICriticaJPA criticaJPA;

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioJPA.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        Optional<Usuario> optional = usuarioJPA.findById(idUsuario);
        return optional.orElse(null);
    }

    @Override
    public List<Usuario> buscarUsuarioPorNombre(String nombre) {
        return usuarioJPA.findByUsernameContainingIgnoreCase(nombre);
    }

    @Override
    public List<Usuario> buscarUsuarioPorCorreo(String correo) {
        return usuarioJPA.findByCorreoContainingIgnoreCase(correo);
    }

    @Override
    public List<Usuario> buscarPorNombreYCorreo(String nombre, String correo) {
        return usuarioJPA.findByUsernameContainingIgnoreCaseAndCorreoContainingIgnoreCase(nombre, correo);
    }

    @Override
    public Usuario buscarPorCorreoYPassword(String correo, String password) {
        Optional<Usuario> o = Optional.ofNullable(usuarioJPA.findByCorreoAndPassword(correo, password));
        return o.orElse(null);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioJPA.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
        usuarioJPA.deleteById(idUsuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioJPA.save(usuario);
    }
}
