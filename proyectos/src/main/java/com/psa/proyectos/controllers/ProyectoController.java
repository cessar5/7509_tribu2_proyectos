package com.psa.proyectos.controllers;


import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping(value = "proyectos")
@CrossOrigin("*")

public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;


    @GetMapping
    public ArrayList<ProyectoModel> obtenerProyectos(){
        return proyectoService.obtenerProyectos();
    }

    @PostMapping
    public ProyectoModel guardarProyecto(@RequestBody ProyectoModel proyecto){
        return this.proyectoService.guardarProyecto(proyecto);
    }

    @GetMapping (path = "/{id}")
    public Optional<ProyectoModel> obtenerProyectosPorID(@PathVariable("id") Long id){
        return this.proyectoService.obtenerPorId(id);
    }

    @PutMapping(path ="/")
    public ProyectoModel actualizarProyecto(@RequestBody ProyectoModel proyecto){
        return this.proyectoService.actualizarProyecto(proyecto);
    }

    @PutMapping(path ="/{id}")
    public String actualizarProyecto(@RequestBody ProyectoModel proyecto, @PathVariable("id") Long id){
        boolean ok = this.proyectoService.actualizarProyectoPorId(proyecto,id);
        if (ok) {
            return "Se actualizo el proyecto con id: "+id;
        }else{
            return "No se pudo actualizo el proyecto con id: "+id;
        }

    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorID(@PathVariable("id") Long id)
    {
        boolean ok = this.proyectoService.eliminarProyecto(id);
        if (ok) {
            return "Se elimino el proyecto con id: "+id;
        }else{
            return "No se pudo eliminar el proyecto con id: "+id;
        }
    }

    @GetMapping("/query")
    public ArrayList<ProyectoModel> obtenerProyectoPorNombre(@RequestParam("nombre") String nombre){
        return this.proyectoService.obtenerPorNombre(nombre);
    }

}
