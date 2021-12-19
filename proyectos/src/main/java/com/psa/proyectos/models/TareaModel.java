package com.psa.proyectos.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "tareas" )
@Table(name = "tareas" )
public class TareaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idTarea;

    @ManyToOne
    @JoinColumn(name="idProyecto", nullable = false)
    private ProyectoModel idProyecto;

    @Column
    private Long idLegajoEmpleado;

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

    @Column
    private Long idTicket;

    @Column
    private String prioridad;

    @Column
    private String estado;

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

    public Long getIdLegajoEmpleado() {
        return idLegajoEmpleado;
    }

    public void setIdLegajoEmpleado(Long idLegajoEmpleado) {
        this.idLegajoEmpleado = idLegajoEmpleado;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
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
