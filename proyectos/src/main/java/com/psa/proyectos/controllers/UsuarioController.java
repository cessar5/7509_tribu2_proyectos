package com.psa.proyectos.controllers;

import com.psa.proyectos.models.UsuarioModel;
import com.psa.proyectos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/usuarios")

public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    /*cuando llegue un get ejecuta esto*/
    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }
    @PostMapping
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping (path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuariosPorID(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorID(@PathVariable("id") Long id)
    {
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se elimino el usuario con id: "+id;
        }else{
            return "No se pudo eliminar el usuario con id: "+id;
        }
    }
    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuariosPorNombre(@RequestParam("nombre") String nombre){

        return this.usuarioService.obtenerPorNombre(nombre);
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
