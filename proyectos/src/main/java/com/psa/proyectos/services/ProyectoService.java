package com.psa.proyectos.services;

import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.models.TareaModel;
import com.psa.proyectos.repositories.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProyectoService {
    @Autowired
    ProyectoRepository proyectoRepository;

    public ArrayList<ProyectoModel> obtenerProyectos(){
        return (ArrayList<ProyectoModel>) proyectoRepository.findAll();
    }
    public ProyectoModel guardarProyecto(ProyectoModel proyecto){
        return proyectoRepository.save(proyecto);
    }

    public Optional<ProyectoModel> obtenerPorId (Long id) {
        return proyectoRepository.findById(id);
    }

    public void eliminarProyecto(Long id){
        //el controller toma la excepcion si no puede borrar
            proyectoRepository.deleteById(id);
    }

    public ProyectoModel actualizarProyecto(ProyectoModel proyecto){
        ProyectoModel anteriorProyecto = proyectoRepository.findById(proyecto.getIdProyecto()).orElse(null);
        //assert anteriorProyecto != null;
        //todo: el proyecto no puede ser nulo
            anteriorProyecto.setDescripcion(proyecto.getDescripcion());
            anteriorProyecto.setNombre(proyecto.getNombre());
            anteriorProyecto.setFechaEntregaComunicadaACliente(proyecto.getFechaEntregaComunicadaACliente());
            anteriorProyecto.setFechaFinalizacionEstimada(proyecto.getFechaFinalizacionEstimada());
            anteriorProyecto.setFechaFinalizacionReal(proyecto.getFechaFinalizacionReal());
            anteriorProyecto.setFechaInicioEstimada(proyecto.getFechaInicioEstimada());
            anteriorProyecto.setFechaInicioReal(proyecto.getFechaInicioReal());
            anteriorProyecto.setIdLegajoLiderProyecto(proyecto.getIdLegajoLiderProyecto());
            anteriorProyecto.setHorasEstimadas(proyecto.getHorasEstimadas());
            anteriorProyecto.setEstado(proyecto.getEstado());
            anteriorProyecto.setPrioridad(proyecto.getPrioridad());

             return proyectoRepository.save(anteriorProyecto);
    }

    public boolean actualizarProyectoPorId(ProyectoModel proyecto, Long idProyecto){
        ProyectoModel anteriorProyecto = proyectoRepository.findById(idProyecto).orElse(null);
        //assert anteriorProyecto != null;
        //todo: el proyecto no puede ser nulo
        try{
            anteriorProyecto.setDescripcion(proyecto.getDescripcion());
            anteriorProyecto.setNombre(proyecto.getNombre());
            anteriorProyecto.setFechaEntregaComunicadaACliente(proyecto.getFechaEntregaComunicadaACliente());
            anteriorProyecto.setFechaFinalizacionEstimada(proyecto.getFechaFinalizacionEstimada());
            anteriorProyecto.setFechaFinalizacionReal(proyecto.getFechaFinalizacionReal());
            anteriorProyecto.setFechaInicioEstimada(proyecto.getFechaInicioEstimada());
            anteriorProyecto.setFechaInicioReal(proyecto.getFechaInicioReal());
            anteriorProyecto.setIdLegajoLiderProyecto(proyecto.getIdLegajoLiderProyecto());
            anteriorProyecto.setHorasEstimadas(proyecto.getHorasEstimadas());
            anteriorProyecto.setEstado(proyecto.getEstado());
            anteriorProyecto.setPrioridad(proyecto.getPrioridad());

            proyectoRepository.save(anteriorProyecto);
            return true;
        }catch(Exception err){
            return false;
        }
    }


    public ArrayList<ProyectoModel> obtenerPorNombreODescripcion(String nombre,String descripcion){
        return proyectoRepository.findByNombreOrDescripcion(nombre,descripcion);
    }


    public ArrayList<TareaModel> obtenerPorIdProyecto(Long idProyecto){
        return proyectoRepository.findByIdProyecto(idProyecto);
    }
}
