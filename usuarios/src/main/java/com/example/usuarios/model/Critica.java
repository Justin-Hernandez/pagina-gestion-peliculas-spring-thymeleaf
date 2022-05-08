package com.example.usuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Criticas", schema = "usersdb")
public class Critica implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCritica")
    private int idCritica;

    @Basic
    @Column(name = "idPelicula")
    private int idPelicula;

    @Basic
    @Column(name = "valoracion")
    private String valoracion;

    @Basic
    @Column(name = "nota")
    private byte nota;

    @Basic
    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name="Users_idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @JsonIgnoreProperties("criticas")
    private Usuario usuario;

    public Critica() {

    }

    public Integer getIdCritica() {
        return idCritica;
    }

    public void setIdCritica(int idCritica) {
        this.idCritica = idCritica;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public byte getNota() {
        return nota;
    }

    public void setNota(byte nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Critica that = (Critica) o;
        return idCritica == that.idCritica && idPelicula == that.idPelicula && nota == that.nota && Objects.equals(valoracion, that.valoracion) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCritica, idPelicula, valoracion, nota, fecha);
    }
}
