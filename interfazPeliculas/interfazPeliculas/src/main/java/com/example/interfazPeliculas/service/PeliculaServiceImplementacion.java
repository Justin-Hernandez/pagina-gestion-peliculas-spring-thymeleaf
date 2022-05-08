package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Pelicula;
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
public class PeliculaServiceImplementacion implements InterfacePeliculaService{

    @Autowired
    RestTemplate template;

    String url = "http://localhost:9000/api/peliculas-actores/peliculas";

    @Override
    public Page<Pelicula> buscarPeliculas(String titulo, String genero, String actor, Pageable pageable) {

        String queryParams = "?";

        if(titulo != null && !titulo.equals(""))
        {
            queryParams = queryParams + "titulo=" + titulo + "&"; //?titulo=Vengadores
        }
        if(genero != null && !genero.equals(""))
        {
            queryParams = queryParams + "genero=" + genero + "&"; //?genero=Aventura
        }
        if(actor != null && !actor.equals(""))
        {
            queryParams = queryParams + "actor=" + actor + "&"; //?actor=Chris
        }

        //elimina el ultimo caracter, que sera & o ? en caso de que no hayan query params
        queryParams = queryParams.substring(0, queryParams.length() - 1);

        Pelicula[] peliculas = template.getForObject(url + queryParams, Pelicula[].class);
        List<Pelicula> peliculasList = Arrays.asList(peliculas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Pelicula> list;

        if (peliculasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, peliculasList.size());
            list = peliculasList.subList(startItem, toIndex);
        }

        Page<Pelicula> page = new PageImpl<>(list, pageable, peliculasList.size());

        return page;
    }

    @Override
    public Pelicula findById(Integer idPelicula) {

        //getForObject() lanza excepcion si status code no es 2XX
        try {
            Pelicula pelicula = template.getForObject(url + "/" + idPelicula, Pelicula.class);
            return pelicula;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insertarPelicula(Pelicula pelicula) {
        template.postForObject(url, pelicula, String.class);
    }

    @Override
    public void eliminarPelicula(Integer idPelicula) {
        template.delete(url + "/" + idPelicula);
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        if(pelicula.getIdPelicula() != null && pelicula.getIdPelicula() > 0){
            template.put(url, pelicula);
        }
    }
}
