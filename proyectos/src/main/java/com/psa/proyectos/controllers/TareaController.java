package com.psa.proyectos.controllers;

import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.models.TareaModel;
import com.psa.proyectos.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "tareas")
@CrossOrigin("*")
public class TareaController {
    @Autowired
    TareaService tareaService;

    @GetMapping
    public ArrayList<TareaModel> obtenerTareas() {
        return tareaService.obtenerTareas();
    }

    @PostMapping
    public TareaModel guardarTarea(@RequestBody TareaModel tarea){
        return this.tareaService.guardarTarea(tarea);
    }

    @GetMapping (path = "/{id}")
    public Optional<TareaModel> obtenerTareasPorID(@PathVariable("id") Long id){
        return this.tareaService.obtenerPorId(id);
    }

    @PutMapping(path ="/")
    public TareaModel actualizarTarea(@RequestBody TareaModel tarea){
        return this.tareaService.actualizarTarea(tarea);
    }

    @PutMapping(path ="/{id}")
    public String actualizarTareaPorId(@RequestBody TareaModel tarea,@PathVariable("id") Long id){
        boolean ok = this.tareaService.actualizarTareaPorId(tarea,id);
        if (ok) {
            return "Se actualizo la tarea con id: "+id;
        }else{
            return "No se pudo actualizo la tarea con id: "+id;
        }

    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorID(@PathVariable("id") Long id)
    {
        boolean ok = this.tareaService.eliminarTarea(id);
        if (ok) {
            return "Se elimino la tarea con id: "+id;
        }else{
            return "No se pudo eliminar la tarea con id: "+id;
        }
    }

    @GetMapping(path = "/query")
    public ArrayList<TareaModel> obtenerTareasPorNombre(@RequestParam("nombre") String nombre){

        return this.tareaService.obtenerPorNombre(nombre);
    }
}
