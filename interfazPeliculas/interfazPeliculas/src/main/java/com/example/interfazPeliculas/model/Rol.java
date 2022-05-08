package com.example.interfazPeliculas.model;

public class Rol {

    private int idRol;
    private String authority;

    public Rol(int idRol, String authority) {
        this.idRol = idRol;
        this.authority = authority;
    }

    public Rol(){

    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
