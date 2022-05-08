package com.example.interfazPeliculas.service;

import com.example.interfazPeliculas.model.Critica;
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
public class CriticaServiceImplementacion implements InterfaceCriticaService{

    @Autowired
    RestTemplate template;

    String url = "http://localhost:9000/api/usuarios-criticas/criticas";

    @Override
    public Page<Critica> buscarCriticasPorIdPelicula(Integer idPelicula, Pageable pageable) {

        String queryParams = "?pelicula=" + idPelicula;

        Critica[] criticas = template.getForObject(url + queryParams, Critica[].class);
        List<Critica> criticasList = Arrays.asList(criticas);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Critica> list;

        if (criticasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }

        Page<Critica> page = new PageImpl<>(list, pageable, criticasList.size());

        return page;
    }

    @Override
    public void guardarCritica(Critica critica) {
        template.postForObject(url, critica, Critica.class);
    }

    @Override
    public void eliminarCritica(Integer idCritica) {
        template.delete(url + "/" + idCritica);
    }

    @Override
    public void actualizarCritica(Critica critica) {
        template.put(url, critica, Critica.class);
    }
}
