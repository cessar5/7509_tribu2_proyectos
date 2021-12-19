package com.psa.proyectos.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity(name = "proyectos" )
@Table(name = "proyectos" )

public class ProyectoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idProyecto;

    //@OneToMany(mappedBy = "idTarea")
    //private List<TareaModel> idTareas = new ArrayList<TareaModel>();

    @Column
    private Long idLegajoLiderProyecto;

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

    @Column
    private Integer horasEstimadas;

    @Column
    private String prioridad;

    @Column
    private String estado;

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

    public Long getIdLegajoLiderProyecto() {
        return idLegajoLiderProyecto;
    }

    public void setIdLegajoLiderProyecto(Long idLegajoLiderProyecto) {
        this.idLegajoLiderProyecto = idLegajoLiderProyecto;
    }

    public Integer getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(Integer horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
