package com.example.portalPeliculas.controller;

import com.example.portalPeliculas.model.Actor;
import com.example.portalPeliculas.model.ResponseClass;
import com.example.portalPeliculas.service.InterfaceActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {

    @Autowired
    InterfaceActorService actoresService;

    @GetMapping("/actores")
    public List<Actor> buscarActores(@RequestParam(value = "pais", required = false) String pais,
                                     @RequestParam(value = "nombre", required = false) String nombre){

        if(pais == null && nombre == null)
        {
            return actoresService.buscarTodos();    //busca todos
        }else if(pais != null && nombre == null)
        {
            return actoresService.findByPais(pais); //por pais
        }else if(pais == null && nombre != null)
        {
            return actoresService.findByNombre(nombre);   //por nombre
        }else{
            return actoresService.findByNombreAndPais(nombre, pais); //por nombre y pais
        }
    }

    @GetMapping("/actores/{id}")
    public Object buscarActorPorId(@PathVariable("id") Integer idActor){

        Actor actor = actoresService.buscarActorPorId(idActor);

        return actor != null ? actor : new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/actores")
    public Object guardarActor(@RequestBody Actor actor){

        Actor actor_aux = actoresService.buscarActorPorId(actor.getIdActor());

        if(actor_aux == null)
        {
            actoresService.guardarActor(actor);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }else
        {
            actor.setIdActor(0);
            actoresService.guardarActor(actor);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }
    }

    @PutMapping("/actores")
    public Object actualizarActor(@RequestBody Actor actor){

        Actor actor_aux = actoresService.buscarActorPorId(actor.getIdActor());

        if(actor_aux == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Actor con id: " + actor.getIdActor() + " no existe"), HttpStatus.NOT_FOUND);
        }else {
            actoresService.actualizarActor(actor);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/actores/{id}")
    public Object eliminarActor(@PathVariable("id") Integer idActor){

        Actor actor = actoresService.buscarActorPorId(idActor);

        if(actor == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
        }else {
            actoresService.eliminarActor(idActor);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }
}
