package com.psa.proyectos.controllers;

import com.psa.proyectos.models.PersonaModel;
import com.psa.proyectos.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "personas")

public class PersonaController {
    @Autowired
    PersonaService personaService;

    /*cuando llegue un get ejecuta esto*/
    @GetMapping()
    public ArrayList<PersonaModel> obtenerPersona(){
        return personaService.obtenerPersona();
    }
    @PostMapping
    public PersonaModel guardarPersona(@RequestBody PersonaModel persona){
        return this.personaService.guardarPersona(persona);
    }

    @GetMapping (path = "/{id_legajo}")
    public Optional<PersonaModel> obtenerPersonaPorID(@PathVariable("id_legajo") Long idLegajo){
        return this.personaService.obtenerPorIdLegajo(idLegajo);
    }

    @DeleteMapping(path = "/{id_legajo}")
    public String eliminarPorIdLegajo(@PathVariable("id_legajo") Long idLegajo)
    {
        boolean ok = this.personaService.eliminarPersona(idLegajo);
        if (ok) {
            return "Se elimino el usuario con legajo: "+idLegajo;
        }else{
            return "No se pudo eliminar el usuario con legajo: "+idLegajo;
        }
    }
    @GetMapping("/query")
    public ArrayList<PersonaModel> obtenerPersonaPorNombre(@RequestParam("nombre") String nombre){

        return this.personaService.obtenerPorNombre(nombre);
    }

    /* da error si hay mas de un /query
    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuariosPorRol(@RequestParam("rol") String rol){

        return this.usuarioService.obtenerPorRol(rol);
    }
    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuariosPorApellido(@RequestParam("apellido") String apellido){

        return this.usuarioService.obtenerPorApellido(apellido);
    }
   */

}
