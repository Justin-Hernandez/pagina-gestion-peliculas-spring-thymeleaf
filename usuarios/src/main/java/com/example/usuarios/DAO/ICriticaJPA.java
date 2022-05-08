package com.example.usuarios.DAO;

import com.example.usuarios.model.Critica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICriticaJPA extends JpaRepository<Critica, Integer> {

    List<Critica> findByidPelicula(Integer idPelicula);
}
