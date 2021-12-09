package com.psa.proyectos.repositories;

import com.psa.proyectos.models.TareaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TareaRepository extends CrudRepository<TareaModel, Long> {
    public abstract ArrayList<TareaModel> findByDescripcion (String descripcion);
    public abstract ArrayList<TareaModel> findByNombre (String nombre);


}
