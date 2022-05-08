package com.example.portalPeliculas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "peliculas", schema = "peliculasdb")
public class Pelicula {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPelicula")
    private Integer idPelicula;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "año")
    private Integer año;
    @Basic
    @Column(name = "duracion")
    private Integer duracion;
    @Basic
    @Column(name = "pais")
    private String pais;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "genero")
    private String genero;
    @Basic
    @Column(name = "sinopsis")
    private String sinopsis;
    @Basic
    @Column(name = "portada")
    private String portada;

    @ManyToMany
    @JoinTable(name="actores_participan_peliculas",
            joinColumns = {@JoinColumn(name = "Peliculas_idPelicula", referencedColumnName = "idPelicula")},
            inverseJoinColumns = {@JoinColumn(name = "Actores_idActor", referencedColumnName = "idActor")})

    @JsonIgnoreProperties("peliculas")
    private List<Actor> actores = new ArrayList<>();

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Object getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    //inserta nuevo actor en la lista
    public void addActor(Actor actor) {
        if (actor != null)
        {
            getActores().add(actor);
        }
    }

    //elimina actor de la lista
    public void removeActor(Actor actor){
        if(actor != null)
        {
            getActores().remove(actor);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula that = (Pelicula) o;
        return Objects.equals(idPelicula, that.idPelicula) && Objects.equals(titulo, that.titulo) && Objects.equals(año, that.año) && Objects.equals(duracion, that.duracion) && Objects.equals(pais, that.pais) && Objects.equals(direccion, that.direccion) && Objects.equals(genero, that.genero) && Objects.equals(sinopsis, that.sinopsis) && Objects.equals(portada, that.portada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPelicula, titulo, año, duracion, pais, direccion, genero, sinopsis, portada);
    }
}
