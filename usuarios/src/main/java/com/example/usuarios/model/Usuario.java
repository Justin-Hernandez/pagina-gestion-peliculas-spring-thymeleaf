package com.example.usuarios.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users", schema = "usersdb")
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "correo")
    private String correo;
    @Basic
    @Column(name = "enable")
    private byte enable;

    public Usuario() {

    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Critica> criticas;

    @ManyToMany
    @JoinTable(name="Users_has_Authorities", joinColumns = {
            @JoinColumn(name="Users_idUsuario", referencedColumnName = "idUsuario")},
             inverseJoinColumns = {
            @JoinColumn(name = "Authorities_idRol", referencedColumnName = "idRol")})
    private List<Rol> roles;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public byte getEnable() {
        return enable;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    public List<Critica> getCriticas() {
        return criticas;
    }

    public void setCriticas(List<Critica> criticas) {
        this.criticas = criticas;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void addCritica( Critica critica){
        getCriticas().add(critica);
        critica.setUsuario(this);
    }

    public void removeCritica(Critica critica)
    {
        if(critica != null)
        {
            getCriticas().remove(critica);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario that = (Usuario) o;
        return idUsuario == that.idUsuario && enable == that.enable && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, username, password, correo, enable);
    }
}
