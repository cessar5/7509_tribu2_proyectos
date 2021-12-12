package com.psa.proyectos.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column
    private String nombre;
    @Column
    private String descripcion;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaInicioReal;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaFinalizacionReal;
    @Column
    private Integer horasEstimadas;
    @Column
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

    public LocalDate getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(LocalDate fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public LocalDate getFechaFinalizacionReal() {
        return fechaFinalizacionReal;
    }

    public void setFechaFinalizacionReal(LocalDate fechaFinalizacionReal) {
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
