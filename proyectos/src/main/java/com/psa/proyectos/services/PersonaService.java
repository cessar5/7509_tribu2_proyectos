package com.psa.proyectos.services;

import com.psa.proyectos.models.PersonaModel;
import com.psa.proyectos.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    public ArrayList<PersonaModel> obtenerPersona(){
        return (ArrayList<PersonaModel>) personaRepository.findAll();
    }
    public PersonaModel guardarPersona(PersonaModel usuario){
        return personaRepository.save(usuario);
    }

    public Optional<PersonaModel> obtenerPorIdLegajo(Long idLegajo) {
        return personaRepository.findById(idLegajo);
    }

    public boolean eliminarPersona(Long idLegajo){
        try{
            personaRepository.deleteById(idLegajo);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    public ArrayList<PersonaModel> obtenerPorNombre(String nombre){
        return personaRepository.findByNombre(nombre);
    }
    public ArrayList<PersonaModel> obtenerPorApellido(String apellido){
        return personaRepository.findByApellido(apellido);
    }

    public ArrayList<PersonaModel> obtenerPorEmail(String email){
        return personaRepository.findByEmail(email);
    }

}
