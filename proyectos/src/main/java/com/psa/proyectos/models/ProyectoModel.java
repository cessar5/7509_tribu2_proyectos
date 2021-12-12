package com.psa.proyectos.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column
    private String nombre;
    @Column
    private String descripcion;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaInicioReal;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  fechaFinalizacionReal;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  fechaInicioEstimada;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  fechaFinalizacionEstimada;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  fechaEntregaComunicadaACliente;

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

    public LocalDate getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(LocalDate fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public LocalDate getFechaFinalizacionReal() {
        return fechaFinalizacionReal;
    }

    public void setFechaFinalizacionReal(LocalDate  fechaFinalizacionReal) {
        this.fechaFinalizacionReal = fechaFinalizacionReal;
    }

    public LocalDate getFechaInicioEstimada() {
        return fechaInicioEstimada;
    }

    public void setFechaInicioEstimada(LocalDate fechaInicioEstimada) {
        this.fechaInicioEstimada = fechaInicioEstimada;
    }

    public LocalDate getFechaFinalizacionEstimada() {
        return fechaFinalizacionEstimada;
    }

    public void setFechaFinalizacionEstimada(LocalDate fechaFinalizacionEstimada) {
        this.fechaFinalizacionEstimada = fechaFinalizacionEstimada;
    }

    public LocalDate getFechaEntregaComunicadaACliente() {
        return fechaEntregaComunicadaACliente;
    }

    public void setFechaEntregaComunicadaACliente(LocalDate  fechaEntregaComunicadaACliente) {
        this.fechaEntregaComunicadaACliente = fechaEntregaComunicadaACliente;
    }

    public PersonaModel getIdLegajo() {
        return idLegajo;
    }

    public void setIdLegajo(PersonaModel idLegajo) {
        this.idLegajo = idLegajo;
    }

}
