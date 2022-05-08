package com.example.usuarios.DAO;

import com.example.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsuarioJPA extends JpaRepository<Usuario, Integer> {

    //individuales
    List<Usuario> findByUsernameContainingIgnoreCase(String username);
    List<Usuario> findByCorreoContainingIgnoreCase(String correo);

    //dobles
    List<Usuario> findByUsernameContainingIgnoreCaseAndCorreoContainingIgnoreCase(String username, String correo);

    //security
    Usuario findByCorreoAndPassword(String correo, String password);
}
