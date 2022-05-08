package com.example.portalPeliculas.DAO;

import com.example.portalPeliculas.model.Pelicula;

import java.util.List;

public interface InterfazPeliculaDAO {

    List<Pelicula> buscarTodos();
    Pelicula buscarPeliculaPorId(Integer idPelicula);

    //individuales
    List<Pelicula> findByTitulo(String titulo);
    List<Pelicula> findByGenero(String genero);
    List<Pelicula> findByActor(String actor);

    //dobles
    List<Pelicula> findByGeneroAndTitulo(String genero, String nombre);
    List<Pelicula> findByGeneroAndActor(String genero, String actor);
    List<Pelicula> findByTituloAndActor(String titulo, String actor);

    //triples
    List<Pelicula> findByTituloAndActorAndGenero(String titulo, String actor, String genero);

    void incluirReparto(Integer idPelicula, Integer idActor);
    void eliminarReparto(Integer idPelicula, Integer idActor);

    void guardarPelicula(Pelicula pelicula);
    void eliminarPelicula(Integer idPelicula);
    void actualizarPelicula(Pelicula pelicula);
}
