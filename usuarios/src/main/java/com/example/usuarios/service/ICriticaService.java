package com.example.usuarios.service;

import com.example.usuarios.model.Critica;

import java.util.List;

public interface ICriticaService {

    List<Critica> buscarTodo();
    Critica buscarCriticaPorId(Integer idCritica);

    List<Critica> buscarCriticaPorIdPelicula(Integer idPelicula);

    void guardarCritica(Critica critica);
    void eliminarCritica(Integer idCritica);
    void actualizarCritica(Critica critica);
}
