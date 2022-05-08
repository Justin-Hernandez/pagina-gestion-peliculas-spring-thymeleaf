package com.example.usuarios.service;

import com.example.usuarios.DAO.ICriticaDAO;
import com.example.usuarios.model.Critica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriticaServiceImplementacion implements ICriticaService{

    @Autowired
    ICriticaDAO criticaDAO;

    @Override
    public List<Critica> buscarTodo() {
        return criticaDAO.buscarTodo();
    }

    @Override
    public Critica buscarCriticaPorId(Integer idCritica) {
        return criticaDAO.buscarCriticaPorId(idCritica);
    }

    @Override
    public List<Critica> buscarCriticaPorIdPelicula(Integer idPelicula) {
        return criticaDAO.buscarCriticaPorIdPelicula(idPelicula);
    }

    @Override
    public void guardarCritica(Critica critica) {
        criticaDAO.guardarCritica(critica);
    }

    @Override
    public void eliminarCritica(Integer idCritica) {
        criticaDAO.eliminarCritica(idCritica);
    }

    @Override
    public void actualizarCritica(Critica critica) {
        criticaDAO.actualizarCritica(critica);
    }
}
