package com.psa.proyectos.repositories;

import com.psa.proyectos.models.ProyectoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//extends CrudRepository<UsuarioModel,Long>
    @Repository
    public interface ProyectoRepository extends CrudRepository<ProyectoModel,Long> {
        public abstract ArrayList<ProyectoModel> findByDescripcion (String descripcion);
        public abstract ArrayList<ProyectoModel> findByNombre (String nombre);

    }
