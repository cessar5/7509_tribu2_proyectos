package com.psa.proyectos.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tareas" )
public class TareaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idTarea;

    @ManyToOne
    @JoinColumn(name="idProyecto", nullable = false)
    private ProyectoModel idProyecto;

    @ManyToOne
    @JoinColumn(name="idLegajo")
    private PersonaModel idLegajo;

    private String nombre;
    private String descripcion;
    private Date fechaInicioReal;
    private Date fechaFinalizacionReal;
    private Integer horasEstimadas;
    private Integer horasTrabajadas;

    private Long idTicket;

    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
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

    public Integer getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(Integer horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public ProyectoModel getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto (ProyectoModel idProyecto) {
        this.idProyecto = idProyecto;
    }

    public PersonaModel getIdLegajo() {
        return idLegajo;
    }

    public void setIdLegajo(PersonaModel idLegajo) {
        this.idLegajo = idLegajo;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }
}
