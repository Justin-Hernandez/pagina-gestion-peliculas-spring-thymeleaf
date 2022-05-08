package com.example.portalPeliculas.service;

import com.example.portalPeliculas.model.Actor;

import java.util.List;

public interface InterfaceActorService {

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
