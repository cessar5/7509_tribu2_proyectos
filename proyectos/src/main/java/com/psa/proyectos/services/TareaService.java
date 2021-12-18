package com.psa.proyectos.services;

import com.psa.proyectos.models.TareaModel;
import com.psa.proyectos.repositories.TareaRepository;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class TareaService {
    @Autowired
    TareaRepository tareaRepository;

    public ArrayList<TareaModel> obtenerTareas(){
        return (ArrayList<TareaModel>) tareaRepository.findAll();
    }
    public TareaModel guardarTarea(TareaModel tarea)
    {
        return tareaRepository.save(tarea);
    }

    public Optional<TareaModel> obtenerPorId (Long id) {
        return tareaRepository.findById(id);
    }
    public void eliminarTarea(Long id){
            //el controller toma la excepcion si no puede borrar
            tareaRepository.deleteById(id);
    }

    public TareaModel actualizarTarea(TareaModel tarea){
        TareaModel anteriorTarea = tareaRepository.findById(tarea.getIdTarea()).orElse(null);
        //assert anteriorTarea != null;
        //todo: el tarea no puede ser nulo
        anteriorTarea.setDescripcion(tarea.getDescripcion());
        anteriorTarea.setNombre(tarea.getNombre());
        anteriorTarea.setFechaFinalizacionReal(tarea.getFechaFinalizacionReal());
        anteriorTarea.setFechaInicioReal(tarea.getFechaInicioReal());
        anteriorTarea.setIdLegajo(tarea.getIdLegajo());
        anteriorTarea.setHorasEstimadas(tarea.getHorasEstimadas());
        anteriorTarea.setHorasTrabajadas(tarea.getHorasTrabajadas());
        anteriorTarea.setIdTicket(tarea.getIdTicket());
        anteriorTarea.setEstado(tarea.getEstado());
        anteriorTarea.setPrioridad(tarea.getPrioridad());

        return tareaRepository.save(anteriorTarea);
    }

    public boolean actualizarTareaPorId(TareaModel tarea, Long idTarea){
        TareaModel anteriorTarea = tareaRepository.findById(idTarea).orElse(null);
        //assert anteriorTarea != null;
        //todo: el tarea no puede ser nulo
        try {
            anteriorTarea.setDescripcion(tarea.getDescripcion());
            anteriorTarea.setNombre(tarea.getNombre());
            anteriorTarea.setFechaFinalizacionReal(tarea.getFechaFinalizacionReal());
            anteriorTarea.setFechaInicioReal(tarea.getFechaInicioReal());
            anteriorTarea.setIdLegajo(tarea.getIdLegajo());
            anteriorTarea.setHorasEstimadas(tarea.getHorasEstimadas());
            anteriorTarea.setHorasTrabajadas(tarea.getHorasTrabajadas());
            anteriorTarea.setIdTicket(tarea.getIdTicket());
            anteriorTarea.setEstado(tarea.getEstado());
            anteriorTarea.setPrioridad(tarea.getPrioridad());


            tareaRepository.save(anteriorTarea);
            return true;
        }catch(Exception err){
            return false;
    }

    }

    @Transactional(readOnly = true)
    public ArrayList<TareaModel> obtenerPorNombreODescripcion(String nombre, String descripcion){
        return tareaRepository.findByNombreOrDescripcion(nombre,descripcion);
    }
    @Transactional(readOnly = true)
    public ArrayList<TareaModel> obtenerPorDescripcion(String descripcion){
        return tareaRepository.findByDescripcion(descripcion);
    }

    @Transactional(readOnly = true)
    public List<TareaModel> obtenerPorIdProyecto(Long idProyecto){
        return tareaRepository.findByIdProyecto(idProyecto);
    }
    @Transactional(readOnly = true)
    public ArrayList<TareaModel> obtenerTareasPorIds(Collection<Long> ids){
        return tareaRepository.findAllByIdTareaIn(ids);
    }
}
