package com.psa.proyectos.repositories;

import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.models.TareaModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//extends CrudRepository<UsuarioModel,Long>
    @Repository
    public interface ProyectoRepository extends CrudRepository<ProyectoModel,Long> {
        public abstract ArrayList<ProyectoModel> findByDescripcion (String descripcion);
    public abstract ArrayList<ProyectoModel> findByNombreOrDescripcion(String nombre, String descripcion);

    @Query("SELECT t FROM tareas t , proyectos c where c.idProyecto = t.idProyecto AND t.idProyecto = :id_proyecto")
    public abstract ArrayList<TareaModel> findByIdProyecto(Long id_proyecto);

    }
