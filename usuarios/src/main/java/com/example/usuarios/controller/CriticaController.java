package com.example.usuarios.controller;

import com.example.usuarios.model.Critica;
import com.example.usuarios.model.ResponseClass;
import com.example.usuarios.service.ICriticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CriticaController {

    @Autowired
    ICriticaService criticaService;

    @GetMapping("/criticas")
    public List<Critica> buscarTodos(@RequestParam(value = "pelicula", required = false) Integer idPelicula){

        if(idPelicula != null)
        {
            return criticaService.buscarCriticaPorIdPelicula(idPelicula);
        }else
        {
            return criticaService.buscarTodo();
        }
    }

    @GetMapping("/criticas/{id}")
    public Object buscarCriticaPorId(@PathVariable("id") Integer id){

        Critica critica = criticaService.buscarCriticaPorId(id);

        return critica != null ? critica: new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/criticas")
    public Object guardarCritica(@RequestBody Critica critica){

        Critica critica_aux = criticaService.buscarCriticaPorId(critica.getIdCritica());

        if(critica_aux == null)
        {
            criticaService.guardarCritica(critica);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }else
        {
            critica.setIdCritica(0);
            criticaService.guardarCritica(critica);
            return new ResponseEntity<ResponseClass>(new ResponseClass("created"), HttpStatus.CREATED);
        }
    }

    @PutMapping("/criticas")
    public Object actualizarCritica(@RequestBody Critica critica){

        Critica critica_aux = criticaService.buscarCriticaPorId(critica.getIdCritica());

        if(critica_aux == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Critica con id: " + critica.getIdCritica() + " no existe"), HttpStatus.NOT_FOUND);
        }else
        {
            criticaService.actualizarCritica(critica);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/criticas/{id}")
    public Object eliminarCritica(@PathVariable("id") Integer id){

        Critica critica = criticaService.buscarCriticaPorId(id);

        if(critica == null)
        {
            return new ResponseEntity<ResponseClass>(new ResponseClass("Not Found"), HttpStatus.NOT_FOUND);
        }else
        {
            criticaService.eliminarCritica(id);
            return new ResponseEntity<ResponseClass>(new ResponseClass(""), HttpStatus.NO_CONTENT);
        }
    }
}
