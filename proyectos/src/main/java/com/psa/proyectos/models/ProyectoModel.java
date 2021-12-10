package com.psa.proyectos.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "proyectos" )

public class ProyectoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idProyecto;

    @ManyToOne
    @JoinColumn(name="idLegajo")
    private PersonaModel idLegajo;

    private String nombre;
    private String descripcion;
    private Date fechaInicioReal;
    private Date fechaFinalizacionReal;

    private Date fechaInicioEstimada;
    private Date fechaFinalizacionEstimada;

    private Date fechaEntregaComunicadaACliente;
    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
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

    public Date getFechaEntregaComunicadaACliente() {
        return fechaEntregaComunicadaACliente;
    }

    public void setFechaEntregaComunicadaACliente(Date fechaEntregaComunicadaACliente) {
        this.fechaEntregaComunicadaACliente = fechaEntregaComunicadaACliente;
    }

    public PersonaModel getIdLegajo() {
        return idLegajo;
    }

    public void setIdLegajo(PersonaModel idLegajo) {
        this.idLegajo = idLegajo;
    }

}
