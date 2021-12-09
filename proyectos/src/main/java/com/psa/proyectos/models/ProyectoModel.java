package com.psa.proyectos.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "proyectos" )

public class ProyectoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre;
    private String descripcion;
    private Date fechaInicioReal;
    private Date fechaFinalizacionReal;

    private Date fechaInicioEstimada;
    private Date fechaFinalizacionEstimada;

    private Date fechaEntregaComunicadaAUsuario;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(Date fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public Date getFechaFinalizacionReal() {
        return fechaFinalizacionReal;
    }

    public void setFechaFinalizacionReal(Date fechaFinalizacionReal) {
        this.fechaFinalizacionReal = fechaFinalizacionReal;
    }

    public Date getFechaInicioEstimada() {
        return fechaInicioEstimada;
    }

    public void setFechaInicioEstimada(Date fechaInicioEstimada) {
        this.fechaInicioEstimada = fechaInicioEstimada;
    }

    public Date getFechaFinalizacionEstimada() {
        return fechaFinalizacionEstimada;
    }

    public void setFechaFinalizacionEstimada(Date fechaFinalizacionEstimada) {
        this.fechaFinalizacionEstimada = fechaFinalizacionEstimada;
    }

    public Date getFechaEntregaComunicadaAUsuario() {
        return fechaEntregaComunicadaAUsuario;
    }

    public void setFechaEntregaComunicadaAUsuario(Date fechaEntregaComunicadaAUsuario) {
        this.fechaEntregaComunicadaAUsuario = fechaEntregaComunicadaAUsuario;
    }


}
