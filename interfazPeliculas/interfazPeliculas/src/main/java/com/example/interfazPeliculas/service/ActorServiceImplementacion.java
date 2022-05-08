package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Actor;
import com.example.interfazPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ActorServiceImplementacion implements InterfaceActorService{

    @Autowired
    RestTemplate template;

    String url = "http://localhost:9000/api/peliculas-actores/actores";

    @Override
    public Page<Actor> buscarActores(String nombre, String pais, Pageable pageable) {

        String queryParams = "?";

        if(nombre != null && !nombre.equals(""))
        {
            queryParams = queryParams + "nombre=" + nombre + "&"; //?nombre=Justin
        }
        if(pais != null && !pais.equals(""))
        {
            queryParams = queryParams + "pais=" + pais + "&"; //?pais=Estados Unidos
        }

        //elimina el ultimo caracter, que sera & o ? en caso de que no hayan query params
        queryParams = queryParams.substring(0, queryParams.length() - 1);

        Actor[] actores = template.getForObject(url + queryParams, Actor[].class);
        List<Actor> actoresList = Arrays.asList(actores);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Actor> list;

        if (actoresList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, actoresList.size());
            list = actoresList.subList(startItem, toIndex);
        }

        Page<Actor> page = new PageImpl<>(list, pageable, actoresList.size());

        return page;
    }

    @Override
    public Actor findById(Integer idActor) {

        //getForObject() lanza excepcion si status code no es 2XX
        try {
            Actor actor = template.getForObject(url + "/" + idActor, Actor.class);
            return actor;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insertarActor(Actor actor) {
        template.postForObject(url, actor, String.class);
    }

    @Override
    public void eliminarActro(Integer idActor) {
        template.delete(url + "/" + idActor);
    }

    @Override
    public void actualizarActor(Actor actor) {
        if(actor.getIdActor() != null && actor.getIdActor() > 0){
            template.put(url, actor);
        }
    }
}
