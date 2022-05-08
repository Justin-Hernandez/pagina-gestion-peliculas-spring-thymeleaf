package com.example.portalPeliculas.DAO;

import com.example.portalPeliculas.model.Actor;
import com.example.portalPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeliculaDAOImplementacion implements InterfazPeliculaDAO {

    @Autowired
    InterfazPeliculaJPA peliculasJPA;

    @Autowired
    InterfazActorJPA actoresJPA;

    @Override
    public List<Pelicula> buscarTodos() {
        return peliculasJPA.findAll();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        Optional<Pelicula> p = peliculasJPA.findById(idPelicula);
        return p.orElse(null);
    }

    @Override
    public List<Pelicula> findByTitulo(String titulo) {
        return peliculasJPA.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Pelicula> findByGenero(String genero) {
        return peliculasJPA.findByGeneroContainingIgnoreCase(genero);
    }

    @Override
    public List<Pelicula> findByActor(String actor) {
        return peliculasJPA.findByActor(actor);
    }

    @Override
    public List<Pelicula> findByGeneroAndTitulo(String genero, String nombre) {
        return peliculasJPA.findByGeneroContainingIgnoreCaseAndTituloContainingIgnoreCase(genero, nombre);
    }

    @Override
    public List<Pelicula> findByGeneroAndActor(String genero, String actor) {
        return peliculasJPA.findByGeneroAndActor(genero, actor);
    }

    @Override
    public List<Pelicula> findByTituloAndActor(String titulo, String actor) {
        return peliculasJPA.findByTituloAndActor(titulo, actor);
    }

    @Override
    public List<Pelicula> findByTituloAndActorAndGenero(String titulo, String actor, String genero) {
        return peliculasJPA.findByTituloAndActorAndGenero(titulo, actor, genero);
    }

    @Override
    public void incluirReparto(Integer idPelicula, Integer idActor) {
        Optional<Pelicula> p = peliculasJPA.findById(idPelicula);
        if(p.isPresent()){
            Pelicula pelicula = p.get();
            Optional<Actor> a = actoresJPA.findById(idActor);
            if(a.isPresent()){
                Actor actor = a.get();

                pelicula.addActor(actor);
                actor.addPelicula(pelicula);

                actoresJPA.save(actor);
                peliculasJPA.save(pelicula);
            }
        }
    }

    @Override
    public void eliminarReparto(Integer idPelicula, Integer idActor) {
        Optional<Pelicula> p = peliculasJPA.findById(idPelicula);

        if(p.isPresent()){
            Pelicula pelicula = p.get();
            Optional<Actor> a = actoresJPA.findById(idActor);
            if(a.isPresent()){
                Actor actor = a.get();

                pelicula.removeActor(actor);
                actor.removePelicula(pelicula);

                actoresJPA.save(actor);
                peliculasJPA.save(pelicula);
            }
        }
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        peliculasJPA.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Integer idPelicula) {
        Optional<Pelicula> o = peliculasJPA.findById(idPelicula);
        if(o.isPresent()){
            Pelicula pelicula = o.get();
            List<Actor> actores = pelicula.getActores();
            for (Actor a: actores){
                a.removePelicula(pelicula);
            }
        }
        peliculasJPA.deleteById(idPelicula);
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        peliculasJPA.save(pelicula);
    }
}
