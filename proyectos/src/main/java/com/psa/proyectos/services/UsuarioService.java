package com.psa.proyectos.services;

import com.psa.proyectos.models.UsuarioModel;
import com.psa.proyectos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }
    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId (Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    public ArrayList<UsuarioModel> obtenerPorRol(String rol){

        return usuarioRepository.findByRol(rol);
    }
    public ArrayList<UsuarioModel> obtenerPorNombre(String nombre){
        return usuarioRepository.findByRol(nombre);
    }
    public ArrayList<UsuarioModel> obtenerPorApellido(String apellido){
        return usuarioRepository.findByRol(apellido);
    }

    public ArrayList<UsuarioModel> obtenerPorEmail(String email){
        return usuarioRepository.findByRol(email);
    }
}
