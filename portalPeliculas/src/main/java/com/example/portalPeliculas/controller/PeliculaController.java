package com.example.portalPeliculas.controller;

import com.example.portalPeliculas.model.Actor;
import com.example.portalPeliculas.model.Pelicula;
import com.example.portalPeliculas.model.ResponseClass;
import com.example.portalPeliculas.service.InterfaceActorService;
import com.example.portalPeliculas.service.InterfacePeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PeliculaController {

    @Autowired
    InterfacePeliculaService peliculasService;

    @Autowired
    InterfaceActorService actoresService;

    //implemento con query params
    @GetMapping("/peliculas")
    public List<Pelicula> buscarPeliculas(@RequestParam(value = "genero", required = false) String genero,
                                          @RequestParam(value = "actor", required = false) String actor,
                                          @RequestParam(value = "titulo", required = false) String titulo){

        //si no hay query params (filtros) devuelve todas las peliculas
        if(genero == null && actor == null && titulo == null)
        {
            return peliculasService.buscarTodos();
        }else if(genero == null && actor == null && titulo != null)
        {
            return peliculasService.findByTitulo(titulo); //por titulo
        }else if(genero == null && actor != null && titulo == null)
        {
            return peliculasService.findByActor(actor); //por actor
        }else if(genero != null && actor == null && titulo == null)
        {
            return peliculasService.findByGenero(genero);   //por genero
        }else if(genero != null && actor != null && titulo == null)
        {
            return peliculasService.findByGeneroAndActor(genero, actor);  //por genero y actor
        }else if(genero != null && actor == null && titulo != null)
        {
            return peliculasService.findByGeneroAndTitulo(genero, titulo); //por genero y titulo
        }else if(genero == null && actor != null && titulo != null)
        {
            return peliculasService.findByTituloAndActor(titulo, actor);  //por titulo y actor
        }else
        {
            return peliculasService.findByTituloAndActorAndGenero(titulo, actor, genero);  //por titulo, actor y genero
        }
    }

    @GetMapping("/peliculas/{id}")
    public Object buscarPeliculaPorId(@PathVariable("id") Integer idPelicula){

        Pelicula pelicula = peliculasService.buscarPeliculaPorId(idPelicula);

        return pelicula != null ? pelicula : new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/peliculas")
    public Object guardarPelicula(@RequestBody Pelicula pelicula){

        Pelicula pelicula_aux = peliculasService.buscarPeliculaPorId(pelicula.getIdPelicula());

        if(pelicula_aux == null)
        {
            peliculasService.guardarPelicula(pelicula);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }else
        {
            pelicula.setIdPelicula(0);
            peliculasService.guardarPelicula(pelicula);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }
    }

    @PutMapping("/peliculas")
    public Object actualizarPelicula(@RequestBody Pelicula pelicula){
        //peliculasService.actualizarPelicula(pelicula);

        Pelicula pelicula_aux = peliculasService.buscarPeliculaPorId(pelicula.getIdPelicula());

        if(pelicula_aux == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Pelicula con id: " + pelicula.getIdPelicula() + " no existe"), HttpStatus.NOT_FOUND);
        }else
        {
            peliculasService.actualizarPelicula(pelicula);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/peliculas/{id}")
    public Object eliminarPelicula(@PathVariable("id") Integer idPelicula){

        Pelicula pelicula = peliculasService.buscarPeliculaPorId(idPelicula);

        if(pelicula == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
        }else
        {
            peliculasService.eliminarPelicula(idPelicula);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/peliculas/{id}/reparto")
    public Object actualizarReparto(@PathVariable("id") Integer idPelicula, @RequestBody List<Integer> ids){


        Pelicula pelicula = peliculasService.buscarPeliculaPorId(idPelicula);

        if(pelicula == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Pelicula con id: " + idPelicula + " no existe"), HttpStatus.NOT_FOUND);
        }else
        {
            List<Integer> existe = new ArrayList<>();
            List<Integer> noExiste = new ArrayList<>();

            for(Integer id: ids){
                Actor actor = actoresService.buscarActorPorId(id);

                if(actor != null)
                {
                    existe.add(id);
                }else
                {
                    noExiste.add(id);
                }
            }

            if(noExiste.size() > 0)
            {
                return new ResponseEntity<ResponseClass>(new ResponseClass("Los actores con id: " + noExiste.toString() + " no existen"), HttpStatus.NOT_FOUND);
            }else
            {
                for (Integer id: existe){
                    peliculasService.incluirReparto(idPelicula, id);
                }
                return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
            }
        }
    }

    @DeleteMapping("/peliculas/{id}/reparto/{id2}")
    public Object eliminarReparto(@PathVariable("id") Integer idPelicula, @PathVariable("id2") Integer idActor){

        Pelicula pelicula = peliculasService.buscarPeliculaPorId(idPelicula);
        Actor actor = actoresService.buscarActorPorId(idActor);

        if(pelicula == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Pelicula con id: " + idPelicula + " no existe"), HttpStatus.NOT_FOUND);
        }else if(actor == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Actor con id: " + idActor + " no existe"), HttpStatus.NOT_FOUND);
        }else
        {
            peliculasService.eliminarReparto(idPelicula, idActor);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }
}
