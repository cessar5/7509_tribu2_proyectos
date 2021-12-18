package com.psa.proyectos.repositories;

import com.psa.proyectos.models.TareaModel;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<TareaModel, Long> {

    public abstract ArrayList<TareaModel> findByDescripcion(String descripcion);
    public abstract ArrayList<TareaModel> findByNombreOrDescripcion(String nombre, String descripcion);
    //public abstract <TareaModel> findByIdProyecto(Long id_proyecto);

    //@Query("select c from tareas c where c.idProyecto = :id_proyecto")
    //@Query("SELECT c FROM tareas c, proyectos d where c.idProyecto = d.idProyecto and c.idProyecto = :id_proyecto")

    @Query("select t from tareas t where t.idProyecto = :idProyecto")
    public abstract List<TareaModel> findByIdProyecto(@Param("idProyecto") Long id_proyecto);

    public abstract ArrayList<TareaModel> findAllByIdTareaIn(Collection<Long> id);
}

