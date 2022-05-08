package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Critica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfaceCriticaService {

    Page<Critica> buscarCriticasPorIdPelicula(Integer idPelicula, Pageable pageable);

    void guardarCritica(Critica critica);
    void eliminarCritica(Integer idCritica);
    void actualizarCritica(Critica critica);
}
