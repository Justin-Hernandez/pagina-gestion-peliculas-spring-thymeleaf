package com.example.portalPeliculas.DAO;

import com.example.portalPeliculas.model.Actor;
import com.example.portalPeliculas.model.Pelicula;

import java.util.List;

public interface InterfazActorDAO {

    List<Actor> buscarTodos();
    Actor buscarActorPorId(Integer idActor);

    //individuales
    List<Actor> findByNombre(String nombre);
    List<Actor> findByPais(String pais);

    //dobles
    List<Actor> findByNombreAndPais(String nombre, String pais);

    void guardarActor(Actor actor);
    void eliminarActor(Integer idActor);
    void actualizarActor(Actor actor);
}
