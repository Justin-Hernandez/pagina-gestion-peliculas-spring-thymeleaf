package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UsuariosServiceImplementacion implements InterfaceUsuarioService {

    @Autowired
    RestTemplate template;

    String url = "http://localhost:9000/api/usuarios-criticas";

    @Override
    public Page<Usuario> buscarUsuarios(String nombre, String correo, Pageable pageable) {

        String queryParams = "?";

        if (nombre != null && !nombre.equals("")) {
            queryParams = queryParams + "nombre=" + nombre + "&"; //?nombre=user1
        }
        if (correo != null && !correo.equals("")) {
            queryParams = queryParams + "correo=" + correo + "&"; //?correo=@edu.uah.es
        }

        //elimina el ultimo caracter, que sera & o ? en caso de que no hayan query params
        queryParams = queryParams.substring(0, queryParams.length() - 1);

        Usuario[] usuarios = template.getForObject(url + "/usuarios" + queryParams, Usuario[].class);
        List<Usuario> usuariosList = Arrays.asList(usuarios);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Usuario> list;

        if (usuariosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, usuariosList.size());
            list = usuariosList.subList(startItem, toIndex);
        }

        Page<Usuario> page = new PageImpl<>(list, pageable, usuariosList.size());

        return page;
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {

        //getForObject() lanza excepcion si status code no es 2XX
        try {
            Usuario[] usuarios = template.getForObject(url + "/usuarios?correo=" + correo, Usuario[].class);
            List<Usuario> usuariosList = Arrays.asList(usuarios);

            return usuariosList.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {

        //getForObject() lanza excepcion si status code no es 2XX
        try {
            Usuario usuario = template.getForObject(url + "/usuarios/" + idUsuario, Usuario.class);
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario login(String correo, String password) {

        //getForObject() lanza excepcion si status code no es 2XX
        try {
            Usuario usuario = template.getForObject(url + "/login/" + correo + "/" + password, Usuario.class);
            return usuario;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        template.postForObject(url + "/usuarios", usuario, Usuario.class);
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {
        template.delete(url + "/usuarios/" + idUsuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        template.put(url + "/usuarios", usuario);
    }
}
