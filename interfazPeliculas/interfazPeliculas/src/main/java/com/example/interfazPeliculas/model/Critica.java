package com.example.interfazPeliculas.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Critica {

    private int idCritica;
    private int idPelicula;
    private String valoracion;
    private byte nota;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Usuario usuario;

    public Critica(){

    }

    public Critica(int idCritica, int idPelicula, String valoracion, byte nota, Date fecha, Usuario usuario) {
        this.idCritica = idCritica;
        this.idPelicula = idPelicula;
        this.valoracion = valoracion;
        this.nota = nota;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public int getIdCritica() {
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
}
