package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfaceActorService {

    Page<Actor> buscarActores(String nombre, String pais, Pageable pageable);
    Actor findById(Integer idActor);

    void insertarActor(Actor actor);
    void eliminarActro(Integer idActor);
    void actualizarActor(Actor actor);
}
