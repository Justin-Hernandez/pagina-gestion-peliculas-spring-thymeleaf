package com.example.usuarios.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Authorities", schema = "usersdb")
public class Rol {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRol")
    private int idRol;

    @Basic
    @Column(name = "authority")
    private String authority;

    public Rol() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol that = (Rol) o;
        return idRol == that.idRol && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, authority);
    }
}
