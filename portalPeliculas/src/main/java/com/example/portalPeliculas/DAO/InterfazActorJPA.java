package com.example.portalPeliculas.DAO;

import com.example.portalPeliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterfazActorJPA extends JpaRepository<Actor, Integer> {

    //individuales
    List<Actor> findByNombreContainingIgnoreCase(String nombre);
    List<Actor> findByPaisContainingIgnoreCase(String pais);

    //dobles
    List<Actor> findByNombreContainingIgnoreCaseAndPaisContainingIgnoreCase(String nombre, String pais);
}
