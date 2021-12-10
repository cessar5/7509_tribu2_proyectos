package com.psa.proyectos.repositories;

import com.psa.proyectos.models.PersonaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaModel,Long> {
    public abstract ArrayList<PersonaModel> findByNombre (String nombre);
    public abstract ArrayList<PersonaModel> findByApellido (String apellido);
    public abstract ArrayList<PersonaModel> findByEmail (String email);


}
