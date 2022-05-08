package com.example.portalPeliculas.service;

import com.example.portalPeliculas.DAO.InterfazActorDAO;
import com.example.portalPeliculas.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImplementation implements InterfaceActorService{

    @Autowired
    InterfazActorDAO actoresDAO;

    @Override
    public List<Actor> buscarTodos() {
        return actoresDAO.buscarTodos();
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        return actoresDAO.buscarActorPorId(idActor);
    }

    @Override
    public List<Actor> findByNombre(String nombre) {
        return actoresDAO.findByNombre(nombre);
    }

    @Override
    public List<Actor> findByPais(String pais) {
        return actoresDAO.findByPais(pais);
    }

    @Override
    public List<Actor> findByNombreAndPais(String nombre, String pais) {
        return actoresDAO.findByNombreAndPais(nombre, pais);
    }

    @Override
    public void guardarActor(Actor actor) {
        if(actoresDAO.buscarActorPorId(actor.getIdActor()) == null){
            actoresDAO.guardarActor(actor);
        }
    }

    @Override
    public void eliminarActor(Integer idActor) {
        if(actoresDAO.buscarActorPorId(idActor) != null){
            actoresDAO.eliminarActor(idActor);
        }
    }

    @Override
    public void actualizarActor(Actor actor) {
        if(actoresDAO.buscarActorPorId(actor.getIdActor()) != null){
            actoresDAO.actualizarActor(actor);
        }
    }
}
