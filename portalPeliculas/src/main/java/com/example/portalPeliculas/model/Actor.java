package com.example.portalPeliculas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "actores", schema = "peliculasdb")
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idActor")
    private Integer idActor;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
    @Basic
    @Column(name = "pais")
    private String pais;

    @ManyToMany(mappedBy = "actores")
    @JsonIgnoreProperties("actores")
    private List<Pelicula> peliculas = new ArrayList<>();

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    //inserta nueva pelicula en la lista
    public void addPelicula(Pelicula pelicula){
        if(pelicula != null)
        {
            getPeliculas().add(pelicula);
        }
    }

    //elimina pelicula de la lista
    public void removePelicula(Pelicula pelicula){
        if(pelicula != null){
            getPeliculas().remove(pelicula);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor that = (Actor) o;
        return Objects.equals(idActor, that.idActor) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(pais, that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActor, nombre, fechaNacimiento, pais);
    }
}
