package com.psa.proyectos.models;

import javax.persistence.*;

@Entity
@Table (name = "personas" )
public class PersonaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idLegajo;

    private String nombre;

    private String apellido;
    private String email;

    public Long getIdLegajo() {
        return idLegajo;
    }

    public void setIdLegajo(Long idLegajo) {
        this.idLegajo = idLegajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
