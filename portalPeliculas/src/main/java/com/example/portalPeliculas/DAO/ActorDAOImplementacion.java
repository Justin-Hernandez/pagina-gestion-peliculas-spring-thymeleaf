package com.example.portalPeliculas.DAO;

import com.example.portalPeliculas.model.Actor;
import com.example.portalPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorDAOImplementacion implements InterfazActorDAO{

    @Autowired
    InterfazActorJPA actoresJPA;

    @Override
    public List<Actor> buscarTodos() {
        return actoresJPA.findAll();
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        Optional<Actor> a = actoresJPA.findById(idActor);
        return a.orElse(null);
    }

    @Override
    public List<Actor> findByNombre(String nombre) {
        return actoresJPA.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Actor> findByPais(String pais) {
        return actoresJPA.findByPaisContainingIgnoreCase(pais);
    }

    @Override
    public List<Actor> findByNombreAndPais(String nombre, String pais) {
        return actoresJPA.findByNombreContainingIgnoreCaseAndPaisContainingIgnoreCase(nombre, pais);
    }

    @Override
    public void guardarActor(Actor actor) {
        actoresJPA.save(actor);
    }

    @Override
    public void eliminarActor(Integer idActor) {
        Optional<Actor> o = actoresJPA.findById(idActor);
        if(o.isPresent())
        {
            Actor actor = o.get();
            List<Pelicula> peliculas = actor.getPeliculas();
            for(Pelicula p: peliculas){
                p.removeActor(actor);
            }
        }
        actoresJPA.deleteById(idActor);
    }

    @Override
    public void actualizarActor(Actor actor) {
        actoresJPA.save(actor);
    }
}
