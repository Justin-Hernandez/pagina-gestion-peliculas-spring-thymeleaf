package com.example.portalPeliculas.DAO;

import com.example.portalPeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterfazPeliculaJPA extends JpaRepository<Pelicula, Integer> {

    //individuales
    List<Pelicula> findByTituloContainingIgnoreCase(String nombre);
    List<Pelicula> findByGeneroContainingIgnoreCase(String genero);
    @Query(value =  "SELECT * FROM peliculas WHERE peliculas.idPelicula IN " +
                    "(SELECT peliculas_idPelicula FROM actores_participan_peliculas WHERE actores_idActor IN " +
                    "(SELECT idActor FROM actores WHERE UPPER(nombre) = upper(?1)))", nativeQuery = true)
    List<Pelicula> findByActor(String name);

    //dobles
    List<Pelicula> findByGeneroContainingIgnoreCaseAndTituloContainingIgnoreCase(String genero, String nombre);
    @Query(value =  "SELECT * FROM peliculas WHERE UPPER(peliculas.genero) LIKE CONCAT('%',UPPER(?1),'%') AND peliculas.idPelicula IN" +
                    "(SELECT peliculas_idPelicula FROM actores_participan_peliculas WHERE actores_idActor IN" +
                    "(SELECT idActor FROM actores WHERE UPPER(nombre) = upper(?2)))", nativeQuery = true)
    List<Pelicula> findByGeneroAndActor(String genero, String actor);
    @Query(value =  "SELECT * FROM peliculas WHERE UPPER(peliculas.titulo) LIKE CONCAT('%',UPPER(?1),'%') AND peliculas.idPelicula IN" +
                    "(SELECT peliculas_idPelicula FROM actores_participan_peliculas WHERE actores_idActor IN" +
                    "(SELECT idActor FROM actores WHERE UPPER(nombre) = upper(?2)))", nativeQuery = true)
    List<Pelicula> findByTituloAndActor(String titulo, String actor);

    //triples
    @Query(value =  "SELECT * FROM peliculas WHERE UPPER(peliculas.titulo) LIKE CONCAT('%',UPPER(?1),'%') AND UPPER(peliculas.genero) LIKE CONCAT('%',UPPER(?3),'%') AND peliculas.idPelicula IN" +
                    "(SELECT peliculas_idPelicula FROM actores_participan_peliculas WHERE actores_idActor IN" +
                    "(SELECT idActor FROM actores WHERE UPPER(nombre) = upper(?2)))", nativeQuery = true)
    List<Pelicula> findByTituloAndActorAndGenero(String titulo, String actor, String genero);
}
