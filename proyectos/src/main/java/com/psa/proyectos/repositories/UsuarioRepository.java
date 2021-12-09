package com.psa.proyectos.repositories;

import com.psa.proyectos.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel,Long> {
    public abstract ArrayList<UsuarioModel> findByRol (String rol);
    public abstract ArrayList<UsuarioModel> findByNombre (String nombre);
    public abstract ArrayList<UsuarioModel> findByApellido (String apellido);
    public abstract ArrayList<UsuarioModel> findByEmail (String email);


}
