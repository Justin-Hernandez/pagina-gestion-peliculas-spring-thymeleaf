package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfacePeliculaService {

    Page<Pelicula> buscarPeliculas(String titulo, String genero, String actor, Pageable pageable);
    Pelicula findById(Integer idPelicula);

    void insertarPelicula(Pelicula pelicula);
    void eliminarPelicula(Integer idPelicula);
    void actualizarPelicula(Pelicula pelicula);
}
