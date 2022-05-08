package com.example.usuarios.DAO;

import com.example.usuarios.model.Critica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CriticaDAOImplementacion implements ICriticaDAO{

    @Autowired
    ICriticaJPA criticaJPA;

    @Override
    public List<Critica> buscarTodo() {
        return criticaJPA.findAll();
    }

    @Override
    public Critica buscarCriticaPorId(Integer idCritica) {
        Optional<Critica> o = criticaJPA.findById(idCritica);
        return o.orElse(null);
    }

    @Override
    public List<Critica> buscarCriticaPorIdPelicula(Integer idPelicula) {
        return criticaJPA.findByidPelicula(idPelicula);
    }

    @Override
    public void guardarCritica(Critica critica) {

        criticaJPA.save(critica);
    }

    @Override
    public void eliminarCritica(Integer idCritica) {
        criticaJPA.deleteById(idCritica);
    }

    @Override
    public void actualizarCritica(Critica critica) {
        criticaJPA.save(critica);
    }
}
