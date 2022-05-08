package com.example.portalPeliculas.service;

import com.example.portalPeliculas.DAO.InterfazPeliculaDAO;
import com.example.portalPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImplementation implements InterfacePeliculaService{

    @Autowired
    InterfazPeliculaDAO peliculasDAO;

    @Override
    public List<Pelicula> buscarTodos() {
        return peliculasDAO.buscarTodos();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        return peliculasDAO.buscarPeliculaPorId(idPelicula);
    }

    @Override
    public List<Pelicula> findByTitulo(String titulo) {
        return peliculasDAO.findByTitulo(titulo);
    }

    @Override
    public List<Pelicula> findByGenero(String genero) {
        return peliculasDAO.findByGenero(genero);
    }

    @Override
    public List<Pelicula> findByActor(String actor) {
        return peliculasDAO.findByActor(actor);
    }

    @Override
    public List<Pelicula> findByGeneroAndTitulo(String genero, String nombre) {
        return peliculasDAO.findByGeneroAndTitulo(genero, nombre);
    }

    @Override
    public List<Pelicula> findByGeneroAndActor(String genero, String actor) {
        return peliculasDAO.findByGeneroAndActor(genero, actor);
    }

    @Override
    public List<Pelicula> findByTituloAndActor(String titulo, String actor) {
        return peliculasDAO.findByTituloAndActor(titulo, actor);
    }

    @Override
    public List<Pelicula> findByTituloAndActorAndGenero(String titulo, String actor, String genero) {
        return peliculasDAO.findByTituloAndActorAndGenero(titulo, actor, genero);
    }

    @Override
    public void incluirReparto(Integer idPelicula, Integer idActor) {
        peliculasDAO.incluirReparto(idPelicula, idActor);
    }

    @Override
    public void eliminarReparto(Integer idPelicula, Integer idActor) {
        peliculasDAO.eliminarReparto(idPelicula, idActor);
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        if(peliculasDAO.buscarPeliculaPorId(pelicula.getIdPelicula()) == null){
            peliculasDAO.guardarPelicula(pelicula);
        }
    }

    @Override
    public void eliminarPelicula(Integer idPelicula) {
        if(peliculasDAO.buscarPeliculaPorId(idPelicula) != null){
            peliculasDAO.eliminarPelicula(idPelicula);
        }
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        if(peliculasDAO.buscarPeliculaPorId(pelicula.getIdPelicula()) != null){
            peliculasDAO.actualizarPelicula(pelicula);
        }
    }
}