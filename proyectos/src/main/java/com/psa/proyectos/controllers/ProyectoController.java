package com.psa.proyectos.controllers;


import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.models.TareaModel;
import com.psa.proyectos.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "proyectos")
@CrossOrigin("*")

public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<ProyectoModel> obtenerProyectos(){
        return proyectoService.obtenerProyectos();
    }

    @PostMapping
    public ResponseEntity<?> guardarProyecto(@RequestBody ProyectoModel proyecto){
        Map<String,Object> response = new HashMap<>();
        System.out.println("hola 1 "+ proyecto.getDescripcion());

        try {
            proyectoService.guardarProyecto(proyecto);
            System.out.println("hola 2"+ proyecto.getDescripcion());
            //en el put esta devolviendo el json que retorna el servicio
            response.put("Mensaje",proyectoService.guardarProyecto(proyecto));

        }catch (DataAccessException e) {
            response.put("Mensaje", "No se pudo guardar el proyecto");
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<?> obtenerProyectosPorID(@PathVariable("id") Long id){
        Map<String,Object> response = new HashMap<>();
        Optional<ProyectoModel> proyectoServices;
        proyectoServices = proyectoService.obtenerPorId(id);

        if (proyectoServices.isPresent()){
            response.put("Mensaje",proyectoServices);
        }else{
            response.put("Error", "No se pudo encontrar el proyecto con id: "+id);
            // response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(path ="/{id}")
    public ResponseEntity<?> actualizarProyecto(@RequestBody ProyectoModel proyecto, @PathVariable("id") Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            boolean ok = this.proyectoService.actualizarProyectoPorId(proyecto,id);
            if (ok) {
                response.put( "Mensaje","Se actualizo el proyecto con id: "+id);
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
            }else{
                response.put("Error", "No se pudo actualizar el proyecto. No existe proyecto con id: "+id);
                // response.put("Error:", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e) {
            response.put("Mensaje", "Ocurrio un error al actualizar el proyecto con id: " + id);
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarPorID(@PathVariable("id") Long id)
    {
        Map<String,Object> response = new HashMap<>();
        try {
            proyectoService.eliminarProyecto(id);
            response.put("Mensaje","Se elimino el proyecto con id: "+id);

        }catch (DataAccessException e) {
            response.put("Mensaje", "No se pudo eliminar el proyecto con id: " + id);
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ArrayList<ProyectoModel> obtenerProyectoPorNombre(@RequestParam("nombre") String nombre
                                                            ,@RequestParam("descripcion") String descripcion){
        return this.proyectoService.obtenerPorNombreODescripcion(nombre,descripcion);
    }

    //@GetMapping(path = "/{id_proyecto}/tareas")
    public ArrayList<TareaModel> obtenerPorParametros(@PathVariable("id_proyecto") Long id_proyecto)
    {
        return this.proyectoService.obtenerPorIdProyecto(id_proyecto);
    }

}
