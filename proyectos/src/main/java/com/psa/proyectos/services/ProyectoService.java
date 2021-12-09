package com.psa.proyectos.services;

import com.psa.proyectos.models.ProyectoModel;
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

    public boolean eliminarProyecto(Long id){
        try{
            proyectoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    public ArrayList<ProyectoModel> obtenerPorNombre(String nombre){
        return proyectoRepository.findByNombre(nombre);
    }
    public ArrayList<ProyectoModel> obtenerPorDescripcion(String descripcion){
        return proyectoRepository.findByNombre(descripcion);
    }  
}
